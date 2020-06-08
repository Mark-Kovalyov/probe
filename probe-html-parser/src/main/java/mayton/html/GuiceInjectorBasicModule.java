package mayton.html;


import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import mayton.html.impl.ConfigImpl;
import mayton.html.impl.DbMemberWriterServiceImpl;
import mayton.html.impl.ElvenNameGeneratorImpl;
import mayton.html.impl.JDBCConnectionPoolComponentImpl;
import mayton.html.mocks.MemberInfoServiceMock;
import mayton.html.mocks.TaskProviderMock;
import mayton.html.mocks.WalkerServiceMock;

public class GuiceInjectorBasicModule extends AbstractModule {

    @Override
    protected void configure() {

        // Connection pool

        bind(ConnectionPoolComponent.class)
                .to(JDBCConnectionPoolComponentImpl.class)
                .in(Scopes.SINGLETON);

        // MemberInfo

        /*bind(MemberInfoService.class)
                .to(MemberInfoServiceImpl.class)
                .in(Scopes.SINGLETON);*/

        bind(MemberInfoService.class)
                //.annotatedWith(Names.named("MemberInfoServiceMock"))
                .to(MemberInfoServiceMock.class)
                .in(Scopes.SINGLETON);

        // Name Generator

        bind(NameGenerator.class)
                .to(ElvenNameGeneratorImpl.class)
                .in(Scopes.SINGLETON);

        // MemberWriterService

        bind(MemberWriterService.class)
                .to(DbMemberWriterServiceImpl.class)
                .in(Scopes.SINGLETON);

        // Walker

        /*bind(WalkerService.class)
                .to(WalkerServiceImpl.class)
                .in(Scopes.SINGLETON);*/

        bind(WalkerService.class)
                //.annotatedWith(Names.named("WalkerServiceMock"))
                .to(WalkerServiceMock.class)
                .in(Scopes.SINGLETON);

        bind(Config.class)
                .to(ConfigImpl.class)
                .in(Scopes.SINGLETON);

        bind(TaskProvider.class)
                .to(TaskProviderMock.class)
                .in(Scopes.SINGLETON);
    }
}
