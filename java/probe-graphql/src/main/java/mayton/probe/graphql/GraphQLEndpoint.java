package mayton.probe.graphql;

import graphql.schema.*;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.servlet.*;

import javax.servlet.annotation.WebServlet;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@WebServlet(name = "HelloServlet", urlPatterns = {"graphql"}, loadOnStartup = 1)
public class GraphQLEndpoint extends GraphQLHttpServlet {

    @Override
    protected GraphQLConfiguration getConfiguration() {
        return GraphQLConfiguration.with(createSchema()).build();
    }

    private GraphQLSchema createSchema() {

        String schema = "type Query{ hello: String }";

        SchemaParser schemaParser = new SchemaParser();

        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();

        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

}
