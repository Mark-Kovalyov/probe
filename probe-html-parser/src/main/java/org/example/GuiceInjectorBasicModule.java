package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class GuiceInjectorBasicModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(MemberInfoService.class)
                .to(MemberInfoServiceImpl.class)
                .in(Scopes.SINGLETON);

        bind(MemberInfoService.class)
                .to(MemberInfoServiceImpl.class)
                .in(Scopes.SINGLETON);

        bind(MemberInfoService.class)
                .annotatedWith(Names.named("MemberInfoServiceMock"))
                .to(MemberInfoServiceMock.class)
                .in(Scopes.SINGLETON);

        bind(NameGenerator.class)
                .annotatedWith(Names.named("ElvenNameGenerator"))
                .to(ElvenNameGenerator.class)
                .in(Scopes.SINGLETON);

        bind(WalkerService.class)
                .to(WalkerServiceImpl.class);

        bind(Config.class)
                .to(ConfigImpl.class);
    }
}
