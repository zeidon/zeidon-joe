package com.quinsoft.zeidon.javascript

object TestGenerate {
   def main(args: Array[String]) {
       val generator = new GenerateXodsForTypescript( args(0), args(1) )
       generator.generate();
    }  
}