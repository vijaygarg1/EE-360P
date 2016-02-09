public class SingleQueue { // Single Producer Single Consumer
  int head = 0;   // slot for get
  int tail = 0;   // empty slot  for put
  Object [] items; 
  public SingleQueue(int size) {
    head = 0; tail = 0;
    items = new Object[size];
  }
  public void put(Object x) {
    while (tail - head == items.length) {}; //busywait
    items[tail % items.length] = x;
    tail++;
  }
  public Object get() {
    while (tail - head == 0) {}; // busywait
    Object x = items[head % items.length];
    head++;
    return x;
  }
}
