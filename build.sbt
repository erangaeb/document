name := "document-service"

description := "Provide document storage"

enablePlugins(ServicePlugin)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:postfixOps")

libraryDependencies ++= {
  val akkaVersion       = "2.3.15"
  val sprayVersion      = "1.3.2"
  val cassandraVersion  = "3.1.1"

  Seq(
    "commons-io"              % "commons-io"                % "2.5",
    "com.datastax.cassandra"  % "cassandra-driver-core"     % cassandraVersion,
    "io.spray"                %% "spray-can"                % sprayVersion,
    "io.spray"                %% "spray-routing"            % sprayVersion,
    "io.spray"                %% "spray-client"             % sprayVersion,
    "io.spray"                %% "spray-json"               % sprayVersion,
    "com.typesafe.akka"       %% "akka-testkit"             % akkaVersion           % "test"
  )
}

parallelExecution in IntegrationTest := false

servicesToDeploy := Seq("etcd", "rabbitmq", "cassandra")
