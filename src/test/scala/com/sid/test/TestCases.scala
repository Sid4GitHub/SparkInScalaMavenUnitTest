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

    val tab1 = spark.read.format("csv").schema(schema).option("header", "true").load("src/test/resources/test_input/input_1.csv")
    
	val tab2 = spark.read.format("csv").schema(schema).option("header", "true").load("src/test/resources/test_input/input_2.csv")	
	
	val expDF = spark.read.format("csv").schema(schema).option("header", "true").load("src/test/resources/test_output/output.csv")	

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