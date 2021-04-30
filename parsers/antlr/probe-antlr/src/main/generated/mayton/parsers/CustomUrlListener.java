// Generated from CustomUrl.g4 by ANTLR 4.9.2

    package mayton.parsers;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CustomUrlParser}.
 */
public interface CustomUrlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CustomUrlParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CustomUrlParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#url}.
	 * @param ctx the parse tree
	 */
	void enterUrl(CustomUrlParser.UrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#url}.
	 * @param ctx the parse tree
	 */
	void exitUrl(CustomUrlParser.UrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#uri}.
	 * @param ctx the parse tree
	 */
	void enterUri(CustomUrlParser.UriContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#uri}.
	 * @param ctx the parse tree
	 */
	void exitUri(CustomUrlParser.UriContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#scheme}.
	 * @param ctx the parse tree
	 */
	void enterScheme(CustomUrlParser.SchemeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#scheme}.
	 * @param ctx the parse tree
	 */
	void exitScheme(CustomUrlParser.SchemeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#host}.
	 * @param ctx the parse tree
	 */
	void enterHost(CustomUrlParser.HostContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#host}.
	 * @param ctx the parse tree
	 */
	void exitHost(CustomUrlParser.HostContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DomainNameOrIPv4Host}
	 * labeled alternative in {@link CustomUrlParser#hostname}.
	 * @param ctx the parse tree
	 */
	void enterDomainNameOrIPv4Host(CustomUrlParser.DomainNameOrIPv4HostContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DomainNameOrIPv4Host}
	 * labeled alternative in {@link CustomUrlParser#hostname}.
	 * @param ctx the parse tree
	 */
	void exitDomainNameOrIPv4Host(CustomUrlParser.DomainNameOrIPv4HostContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IPv6Host}
	 * labeled alternative in {@link CustomUrlParser#hostname}.
	 * @param ctx the parse tree
	 */
	void enterIPv6Host(CustomUrlParser.IPv6HostContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IPv6Host}
	 * labeled alternative in {@link CustomUrlParser#hostname}.
	 * @param ctx the parse tree
	 */
	void exitIPv6Host(CustomUrlParser.IPv6HostContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#v6host}.
	 * @param ctx the parse tree
	 */
	void enterV6host(CustomUrlParser.V6hostContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#v6host}.
	 * @param ctx the parse tree
	 */
	void exitV6host(CustomUrlParser.V6hostContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#port}.
	 * @param ctx the parse tree
	 */
	void enterPort(CustomUrlParser.PortContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#port}.
	 * @param ctx the parse tree
	 */
	void exitPort(CustomUrlParser.PortContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(CustomUrlParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(CustomUrlParser.PathContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#user}.
	 * @param ctx the parse tree
	 */
	void enterUser(CustomUrlParser.UserContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#user}.
	 * @param ctx the parse tree
	 */
	void exitUser(CustomUrlParser.UserContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#login}.
	 * @param ctx the parse tree
	 */
	void enterLogin(CustomUrlParser.LoginContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#login}.
	 * @param ctx the parse tree
	 */
	void exitLogin(CustomUrlParser.LoginContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#password}.
	 * @param ctx the parse tree
	 */
	void enterPassword(CustomUrlParser.PasswordContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#password}.
	 * @param ctx the parse tree
	 */
	void exitPassword(CustomUrlParser.PasswordContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#frag}.
	 * @param ctx the parse tree
	 */
	void enterFrag(CustomUrlParser.FragContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#frag}.
	 * @param ctx the parse tree
	 */
	void exitFrag(CustomUrlParser.FragContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(CustomUrlParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(CustomUrlParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#search}.
	 * @param ctx the parse tree
	 */
	void enterSearch(CustomUrlParser.SearchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#search}.
	 * @param ctx the parse tree
	 */
	void exitSearch(CustomUrlParser.SearchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#searchparameter}.
	 * @param ctx the parse tree
	 */
	void enterSearchparameter(CustomUrlParser.SearchparameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#searchparameter}.
	 * @param ctx the parse tree
	 */
	void exitSearchparameter(CustomUrlParser.SearchparameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CustomUrlParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(CustomUrlParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link CustomUrlParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(CustomUrlParser.StringContext ctx);
}