import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation._
import net.exoego.facade.aws_lambda._

object Handler {

  def main(
      event: APIGatewayProxyEvent
  )(implicit ec: ExecutionContext): Future[APIGatewayProxyResult] = Future {
    APIGatewayProxyResult(
      statusCode = 200,
      body = "hello " + event.body,
      headers = js.defined(js.Dictionary("Content-Type" -> "text/plain"))
    )
  }

  @JSExportTopLevel(name = "handler")
  val handler: js.Function2[APIGatewayProxyEvent, Context, js.Promise[
    APIGatewayProxyResult
  ]] = { (event: APIGatewayProxyEvent, _: Context) =>
    import js.JSConverters._
    implicit val ec = ExecutionContext.global
    main(event).toJSPromise
  }

}
