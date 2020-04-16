## Web app with scala js and Akka HTTP


# observations
1. jsdom needed for dom testing since browser api's are not available at node env.
2. use jest facades for scala.js, since common js modules was not compatible with jsdomNodeEnv plugin for dom testing
3. scala js development is not smooth as compared to typescript. it needs workaround at places to make it work.

# future steps
1. explore testing libraries
2. scala-js-bundler can be used in place of writing jest facades to overcome the common js modules incompatibility issue.
3. explore scalajs-webpack-loader plugin.
