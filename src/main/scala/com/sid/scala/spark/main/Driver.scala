package com.sid.scala.spark.main

import com.sid.scala.spark.common._
import org.apache.spark.sql._
import org.apache.spark.sql.Encoders

case class A(col1:String)

object Driver {

  def main(args : Array[String]) {
    println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n***Start***\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" )
    val spark=GetSparkSession.getSession()
    import spark.implicits._

    val schemaTab1 = Encoders.product[A].schema

    val tab1 = Seq("t1","t2").toDF

    val r2=Seq(
      Row("t3"),
      Row("t4")
    )

    val tab2 = spark.createDataFrame(
      spark.sparkContext.parallelize(r2),
      schemaTab1
    )

    returnUnion(tab1,tab2).show

    StopSparkSession.stop()
    println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n***End***\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" )
  }
  
  def returnUnion(df1:DataFrame,df2:DataFrame):DataFrame={
	  df1.union(df2)	  
  }
}
