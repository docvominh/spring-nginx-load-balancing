package test;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class GetProductSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("text/html")
            .userAgentHeader("Gatling/Performance Test");


    ScenarioBuilder scn = CoreDsl.scenario("Load Test Get Product")
            .exec(http("get-product-request")
                    .get("/")
                    .check(status().is(200))
            );

    {
        this.setUp(scn.injectOpen(constantUsersPerSec(40)
                .during(Duration.ofSeconds(60))))
                .protocols(httpProtocol);
    }

}
