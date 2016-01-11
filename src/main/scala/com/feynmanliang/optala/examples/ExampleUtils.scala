package com.feynmanliang.optala.examples

import java.io.File

import breeze.linalg.{DenseVector, Matrix, Vector, csvwrite}
import breeze.stats.distributions.{RandBasis, Uniform}
import com.feynmanliang.optala.Solution
import com.feynmanliang.optala.neldermead.Simplex

/** Utility functions for example code and experiments. */
private[examples] object ExampleUtils {
  /** Runs an experiment and writes the results to a CSV. */
  def experimentWithResults(
      experimentName: String,
      resultFName: String)(results: => Matrix[Double]) = {
    println(s"=== Performing experiment: $experimentName ===")
    val resultsFile = new File(s"results/$resultFName")
    println(s"Writing results to: $resultsFile")
    csvwrite(resultsFile, results)
  }

  /** Creates a random `n` point simplex over [-2,2] x [-1,1].
    * @param n number of simplex vertices
    * @param f objective function
    * @param rand seed for Breeze random number generator
    */
  def createRandomSimplex(n: Int, f: Vector[Double] => Double)(
      implicit rand: RandBasis): Simplex = Simplex(Seq.fill(n) {
    val simplexPoint = DenseVector(Uniform(-2D, 2D)(rand).sample(), Uniform(-1D, 1D)(rand).sample())
    Solution(f, simplexPoint)
  })
}
