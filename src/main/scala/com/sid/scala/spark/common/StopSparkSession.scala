package com.sid.scala.spark.common

object StopSparkSession {
  def stop(): Unit ={
    GetSparkSession.getSession().stop()
  }
}
