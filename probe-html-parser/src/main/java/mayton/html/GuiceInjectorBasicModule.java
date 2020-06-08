package mayton.html;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import mayton.html.impl.*;
import mayton.html.mocks.MemberInfoServiceMock;
import mayton.html.mocks.TaskProviderMock;
import mayton.html.mocks.WalkerServiceMock;

import static com.google.inject.name.Names.named;

public class GuiceInjectorBasicModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(DynamicDictionaryService.class)
                .to(JDBCDynamicDictionaryServiceImpl.class)
                .in(Scopes.SINGLETON);

        // Connection pool

        bind(ConnectionPoolComponent.class)
                .to(JDBCConnectionPoolComponentImpl.class)
                .in(Scopes.SINGLETON);

        // MemberInfo

        bind(MemberInfoService.class)
                .to(MemberInfoServiceImpl.class)
                .in(Scopes.SINGLETON);

        bind(MemberInfoService.class)
                .annotatedWith(named("MemberInfoServiceMock"))
                .to(MemberInfoServiceMock.class)
                .in(Scopes.SINGLETON);

        // Name Generator

        bind(NameGenerator.class)
                .to(ElvenNameGeneratorImpl.class)
                .in(Scopes.SINGLETON);

        // MemberWriterService

        bind(MemberWriterService.class)
                .to(JDBCMemberWriterServiceImpl.class)
                .in(Scopes.SINGLETON);

        // Walker

        // TODO: Unable to locate WalkerService root component with @Named in 'main'
        bind(WalkerService.class)
                .to(HttpWalkerServiceImpl.class)
                .in(Scopes.SINGLETON);

        bind(Config.class)
                .to(ConfigImpl.class)
                .in(Scopes.SINGLETON);

        // Task provider

        bind(TaskProvider.class)
                .annotatedWith(named("TaskProviderMock"))
                .to(TaskProviderMock.class)
                .in(Scopes.SINGLETON);

        bind(TaskProvider.class)
                .to(JDBCTaskProviderImpl.class)
                .in(Scopes.SINGLETON);

    }
}
