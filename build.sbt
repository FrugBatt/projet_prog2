import scala.scalanative.build.*

scalaVersion := "3.2.2"
version := "0.1.0"
nativeConfig ~= {
  _.withIncrementalCompilation(true)
    .withLTO(LTO.none)
    .withMode(Mode.debug)
}

enablePlugins(ScalaNativePlugin)

githubSuppressPublicationWarning := true
githubTokenSource := TokenSource.GitConfig("github.token")

resolvers += Resolver.githubPackages("lafeychine")
libraryDependencies += "io.github.lafeychine" %%% "scala-native-sfml" % "0.3.7"
