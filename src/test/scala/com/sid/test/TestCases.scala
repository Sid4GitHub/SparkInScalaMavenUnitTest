import com.holdenkarau.spark.testing._
import com.sid.scala.spark.main.Driver.returnUnion
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.types.DataTypes.StringType
import org.apache.spark.sql.{Encoders, Row}
import org.scalatest.FunSuite
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, Matchers}

class TestCases extends FunSuite with DataFrameSuiteBase{
  test("Test returnUnion Method"){

    val schema=StructType(Array(StructField("col1",StringType,false),StructField("col2",DataTypes.IntegerType,false)))

    val r1=Seq(
      Row("t1",1),
      Row("t2",2)
    )

    val tab1 = spark.createDataFrame(
      spark.sparkContext.parallelize(r1),
      schema
    )

    val r2=Seq(
      Row("t3",3),
      Row("t4",4)
    )

    val tab2 = spark.createDataFrame(
      spark.sparkContext.parallelize(r2),
      schema
    )

    val r3=Seq(
      Row("t1",1),
      Row("t2",2),
      Row("t3",3),
      Row("t3",3),
      Row("t4",4)
    )

    val expDF = spark.createDataFrame(
      spark.sparkContext.parallelize(r3),
      schema
    )

    val resDF=returnUnion(tab1,tab2)

    resDF.printSchema()
    expDF.printSchema()

    resDF.show()
    expDF.show()


    assertDataFrameDataEquals(expDF,resDF)

    /*val resC=resDF.collect()
    val expC=expDF.collect()
    var cou=0

    for(i<- 0 to resC.size){
      for(j<-0 to resC(0).size){
        if(resC(i)(j)!=expC(i)(j)){
          cou+=1
        }
      }
    }

    assert(cou,0)*/
	}
}