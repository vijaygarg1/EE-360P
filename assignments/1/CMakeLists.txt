cmake_minimum_required(VERSION 3.0)
project (pset9)
include (../UseLATEX.cmake)

set (doc-staging-area "./doc-staging")

add_latex_document (
  9.tex
  # CONFIGURE 9_new.tex
  DEFAULT_PDF
)
