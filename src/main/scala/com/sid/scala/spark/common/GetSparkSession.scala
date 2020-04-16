package com.sid.scala.spark.common

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object GetSparkSession {
  val conf = new SparkConf().setAppName("Sid")
  //val sc=new SparkContext(conf)

  val spark = SparkSession.builder()
    .config(conf)
    .getOrCreate()
  def getSession(): SparkSession ={
    return spark
  }
}
