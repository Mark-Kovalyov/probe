package mayton.probe.scott;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import mayton.probe.QueryParameters;
import org.dataloader.DataLoaderRegistry;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static graphql.ExecutionInput.newExecutionInput;
import static graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions.newOptions;
import static java.util.Arrays.asList;
import static mayton.probe.JsonKit.returnAsJson;

public class ScottSchemaHandler extends AbstractHandler {

    static Logger logger = LoggerFactory.getLogger(ScottSchemaHandler.class);

    static GraphQLSchema scottSchema = null;

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if ("/graphql".equals(target)) {
            handleScott(request, response);
            baseRequest.setHandled(true);
        }
    }

    private void handleScott(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        //
        // this builds out the parameters we need like the graphql query from the http request
        QueryParameters parameters = QueryParameters.from(httpRequest);
        if (parameters.getQuery() == null) {
            //
            // how to handle nonsensical requests is up to your application
            httpResponse.setStatus(400);
            return;
        }

        //
        // This example uses the DataLoader technique to ensure that the most efficient
        // loading of data (in this case StarWars characters) happens.
        //
        DataLoaderRegistry dataLoaderRegistry = buildScottDataLoaderRegistry();


        ExecutionInput.Builder executionInput = newExecutionInput()
                .query(parameters.getQuery())
                .operationName(parameters.getOperationName())
                .variables(parameters.getVariables())
                .dataLoaderRegistry(dataLoaderRegistry);


        //
        // the context object is something that means something to down stream code.  It is instructions
        // from yourself to your other code such as DataFetchers.  The engine passes this on unchanged and
        // makes it available to inner code
        //
        // the graphql guidance says  :
        //
        //  - GraphQL should be placed after all authentication middleware, so that you
        //  - have access to the same session and user information you would in your
        //  - HTTP endpoint handlers.
        //
        Map<String, Object> context = new HashMap<>();
        context.put("YouAppSecurityClearanceLevel", "CodeRed");
        context.put("YouAppExecutingUser", "Dr Nefarious");
        executionInput.context(context);

        //
        // you need a schema in order to execute queries
        GraphQLSchema schema = buildScottSchema();

        DataLoaderDispatcherInstrumentation dlInstrumentation =
                new DataLoaderDispatcherInstrumentation(newOptions().includeStatistics(true));

        Instrumentation instrumentation = new ChainedInstrumentation(
                asList(new TracingInstrumentation(), dlInstrumentation)
        );

        // finally you build a runtime graphql object and execute the query
        GraphQL graphQL = GraphQL
                .newGraphQL(schema)
                // instrumentation is pluggable
                .instrumentation(instrumentation)
                .build();
        ExecutionResult executionResult = graphQL.execute(executionInput.build());

        returnAsJson(httpResponse, executionResult);
    }

    private DataLoaderRegistry buildScottDataLoaderRegistry() {

        return null;
    }

    private GraphQLSchema buildScottSchema() {
        if (scottSchema == null) {

            // TODO:
            TypeDefinitionRegistry typeRegistry = null;

            // TODO:
            RuntimeWiring wiring = null;



            scottSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        }
        return scottSchema;
    }

}
