package paxos;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * This is a subset of entire test cases
 * For your reference only.
 */
public class PaxosTest {

    private int ndecided(Paxos[] pxa, int seq){
        int counter = 0;
        Object v = null;
        Paxos.retStatus ret;
        for(int i = 0; i < pxa.length; i++){
            if(pxa[i] != null){
                ret = pxa[i].Status(seq);
                if(ret.state == State.Decided) {
                    assertFalse("decided values do not match: seq=" + seq + " i=" + i + " v=" + v + " v1=" + ret.v, counter > 0 && !v.equals(ret.v));
                    counter++;
                    v = ret.v;
                }

            }
        }
        return counter;
    }

    private void waitn(Paxos[] pxa, int seq, int wanted){
        int to = 10;
        for(int i = 0; i < 30; i++){
            if(ndecided(pxa, seq) >= wanted){
                break;
            }
            try {
                Thread.sleep(to);
            } catch (Exception e){
                e.printStackTrace();
            }
            if(to < 1000){
                to = to * 2;
            }
        }

        int nd = ndecided(pxa, seq);
        assertFalse("too few decided; seq=" + seq + " ndecided=" + nd + " wanted=" + wanted, nd < wanted);

    }

    private void waitmajority(Paxos[] pxa, int seq){
        waitn(pxa, seq, (pxa.length/2) + 1);
    }

    private void cleanup(Paxos[] pxa){
        for(int i = 0; i < pxa.length; i++){
            if(pxa[i] != null){
                pxa[i].Kill();
            }
        }
    }

    private Paxos[] initPaxos(int npaxos){
        String host = "127.0.0.1";
        String[] peers = new String[npaxos];
        int[] ports = new int[npaxos];
        Paxos[] pxa = new Paxos[npaxos];
        for(int i = 0 ; i < npaxos; i++){
            ports[i] = 1100+i;
            peers[i] = host;
        }
        for(int i = 0; i < npaxos; i++){
            pxa[i] = new Paxos(i, peers, ports);
        }
        return pxa;
    }

    @Test
    public void TestBasic(){

        final int npaxos = 5;
        Paxos[] pxa = initPaxos(npaxos);

        System.out.println("Test: Single proposer ...");
        pxa[0].Start(0, "hello");
        waitn(pxa, 0, npaxos);
        System.out.println("... Passed");


        System.out.println("Test: Many proposers, same value ...");
        for(int i = 0; i < npaxos; i++){
            pxa[i].Start(1, 77);
        }
        waitn(pxa, 1, npaxos);
        System.out.println("... Passed");

        System.out.println("Test: Many proposers, different values ...");
        pxa[0].Start(2, 100);
        pxa[1].Start(2, 101);
        pxa[2].Start(2, 102);
        waitn(pxa, 2, npaxos);
        System.out.println("... Passed");

        System.out.println("Test: Out-of-order instances ...");
        pxa[0].Start(7, 700);
        try {
            Thread.sleep(10);
        } catch (Exception e){
            e.printStackTrace();
        }
        pxa[0].Start(6, 600);
        pxa[1].Start(5, 500);
        waitn(pxa, 7, npaxos);
        pxa[0].Start(4, 400);
        pxa[1].Start(3, 300);
        waitn(pxa, 6, npaxos);
        waitn(pxa, 5, npaxos);
        waitn(pxa, 4, npaxos);
        waitn(pxa, 3, npaxos);
        System.out.println("... Passed");
        cleanup(pxa);

    }

    @Test
    public void TestDeaf(){

        final int npaxos = 5;
        Paxos[] pxa = initPaxos(npaxos);

        System.out.println("Test: Deaf proposer ...");
        pxa[0].Start(0, "hello");
        waitn(pxa, 0, npaxos);

        pxa[1].ports[0]= 1;
        pxa[1].ports[npaxos-1]= 1;
        pxa[1].Start(1, "goodbye");
        waitmajority(pxa, 1);
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
        int nd = ndecided(pxa, 1);
        assertFalse("a deaf peer heard about a decision " + nd, nd != npaxos-2);

        pxa[0].Start(1, "xxx");
        waitn(pxa, 1, npaxos-1);
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
        nd = ndecided(pxa, 1);
        assertFalse("a deaf peer heard about a decision " + nd, nd != npaxos-1);

        pxa[npaxos-1].Start(1, "yyy");
        waitn(pxa, 1, npaxos);
        System.out.println("... Passed");
        cleanup(pxa);

    }

    @Test
    public void TestForget(){

        final int npaxos = 6;
        Paxos[] pxa = initPaxos(npaxos);

        System.out.println("Test: Forgetting ...");

        for(int i = 0; i < npaxos; i++){
            int m = pxa[i].Min();
            assertFalse("Wrong initial Min() " + m, m > 0);
        }

        pxa[0].Start(0,"00");
        pxa[1].Start(1,"11");
        pxa[2].Start(2,"22");
        pxa[0].Start(6,"66");
        pxa[1].Start(7,"77");

        waitn(pxa, 0, npaxos);
        for(int i = 0; i < npaxos; i++){
            int m = pxa[i].Min();
            assertFalse("Wrong Min() " + m + "; expected 0", m != 0);
        }

        waitn(pxa, 1, npaxos);
        for(int i = 0; i < npaxos; i++){
            int m = pxa[i].Min();
            assertFalse("Wrong Min() " + m + "; expected 0", m != 0);
        }

        for(int i = 0; i < npaxos; i++){
            pxa[i].Done(0);
        }

        for(int i = 1; i < npaxos; i++){
            pxa[i].Done(1);
        }

        for(int i = 0; i < npaxos; i++){
            pxa[i].Start(8+i, "xx");
        }

        boolean ok = false;
        for(int iters = 0; iters < 12; iters++){
            ok = true;
            for(int i = 0; i < npaxos; i++){
                int s = pxa[i].Min();
                if(s != 1){
                    ok = false;
                }
            }
            if(ok) break;
            try {
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        assertFalse("Min() did not advance after Done()", ok != true);
        System.out.println("... Passed");
        cleanup(pxa);


    }


}
