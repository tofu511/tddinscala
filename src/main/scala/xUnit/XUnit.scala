package xUnit

object XUnit {
  def main(args: Array[String]): Unit = {
    TestCaseTest("testRunning").run()
    TestCaseTest("testSetUp").run()
  }
}

case class WasRun(testName: String) extends TestCase {
  var wasSetUp: Boolean = false
  var wasRun: Boolean = false
  // 動的に呼び出したい
  def run(): Unit = testName match {
    case "testMethod" => testMethod()
    case "testSetUp" => setUp()
  }
  def testMethod() : Unit = wasRun = true
  def setUp(): Unit = wasSetUp = true

  override val name: String = testName
}

case class TestCaseTest(testCase: String) extends TestCase {
  // 動的にテストを呼び出せるようになったらWasRunインスタンスを一つにまとめたい
  def testRunning(): Unit = {
    val test = WasRun("testMethod")
    test.run()
    assert(test.wasRun)
  }

  def testSetUp(): Unit = {
    val test = WasRun("testSetUp")
    test.run()
    assert(test.wasSetUp)
  }

  override val name: String = testCase

  // 動的に呼び出したい
  override def run(): Unit = testCase match {
    case "testRunning" => testRunning()
    case "testSetUp" => testSetUp()
  }
}

trait TestCase {
  val name: String
  def run(): Unit
}
