package mayton.probe.starwars;

import graphql.schema.GraphQLObjectType;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class StarWars {

    /**
     * type Foo {
     *         bar: String
     *     }
     *
     */
    public static GraphQLObjectType getObjectType() {
        return newObject()
                .name("Foo")
                .field(newFieldDefinition()
                        .name("bar")
                        .type(GraphQLString))
                .build();
    }

    public static RuntimeWiring buildRuntimeWiring() {
        return null;
//        return RuntimeWiring.newRuntimeWiring()
//                .scalar(CustomScalar)
//                // this uses builder function lambda syntax
//                .type("QueryType", typeWiring -> typeWiring
//                        .dataFetcher("hero", new StaticDataFetcher(StarWarsData.getArtoo()))
//                        .dataFetcher("human", StarWarsData.getHumanDataFetcher())
//                        .dataFetcher("droid", StarWarsData.getDroidDataFetcher())
//                )
//                .type("Human", typeWiring -> typeWiring
//                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
//                )
//                // you can use builder syntax if you don't like the lambda syntax
//                .type("Droid", typeWiring -> typeWiring
//                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
//                )
//                // or full builder syntax if that takes your fancy
//                .type(
//                        newTypeWiring("Character")
//                                .typeResolver(StarWarsData.getCharacterTypeResolver())
//                                .build()
//                )
//                .build();
    }

}
