cmake_minimum_required(VERSION 3.0)
project (pset1)
include (../UseLATEX.cmake)

set (doc-staging-area "./doc-staging")

add_latex_document (
  1.tex
  # CONFIGURE 9_new.tex
  DEFAULT_PDF
)
