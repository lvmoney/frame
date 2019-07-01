package com.lvmoney.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by lvmoney on 2018/12/5.
  */
object Test341 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount2")
    conf.setMaster("spark://192.168.1.102:7077")
    val sc = new SparkContext(conf)
    var rdd = sc.parallelize(1 to 10)
    rdd.collect().foreach((a) => println(a))
    println(rdd.partitions.size)
    var rddf = sc.textFile("hdfs://192.168.1.102:9000/input/test.txt")
    println(rddf.count())
  }
}
