package xUnit

object XUnit {
  def main(args: Array[String]): Unit = {
    TestCaseTest("testRunning").run()
  }
}

case class WasRun(testName: String) extends TestCase {
  var wasRun: Boolean = false
  def run(): Unit = this.testMethod()
  def testMethod() : Unit = wasRun = true

  override val name: String = testName
}

case class TestCaseTest(testCase: String) extends TestCase {
  def testRunning(): Unit= {
    val test = WasRun("testMethod")
    assert(!test.wasRun)
    test.run()
    assert(test.wasRun)
  }

  override val name: String = testCase
  override def run(): Unit = testRunning()
}

trait TestCase {
  val name: String
  def run(): Unit
}
