package movie

import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.Int
import kotlin.String
import kotlin.Unit
import norm.ParamSetter
import norm.Query
import norm.RowMapper

public data class SaveMovieParams(
  public val title: String?,
  public val duration: Int?
)

public class SaveMovieParamSetter : ParamSetter<SaveMovieParams> {
  public override fun map(ps: PreparedStatement, params: SaveMovieParams): Unit {
    ps.setObject(1, params.title)
    ps.setObject(2, params.duration)
  }
}

public class SaveMovieRowMapper : RowMapper<SaveMovieResult> {
  public override fun map(rs: ResultSet): SaveMovieResult = SaveMovieResult(
  id = rs.getObject("id") as kotlin.Int,
    title = rs.getObject("title") as kotlin.String,
    duration = rs.getObject("duration") as kotlin.Int?)
}

public class SaveMovieQuery : Query<SaveMovieParams, SaveMovieResult> {
  public override val sql: String = """
      |INSERT INTO movies(title, duration)
      |VALUES (?, ?)
      |returning *;
      |""".trimMargin()

  public override val mapper: RowMapper<SaveMovieResult> = SaveMovieRowMapper()

  public override val paramSetter: ParamSetter<SaveMovieParams> = SaveMovieParamSetter()
}

public data class SaveMovieResult(
  public val id: Int,
  public val title: String,
  public val duration: Int?
)
