// Generated from CustomUrl.g4 by ANTLR 4.9.2

    package mayton.parsers;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CustomUrlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CustomUrlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CustomUrlParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#url}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUrl(CustomUrlParser.UrlContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#uri}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUri(CustomUrlParser.UriContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#scheme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScheme(CustomUrlParser.SchemeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#host}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHost(CustomUrlParser.HostContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DomainNameOrIPv4Host}
	 * labeled alternative in {@link CustomUrlParser#hostname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainNameOrIPv4Host(CustomUrlParser.DomainNameOrIPv4HostContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IPv6Host}
	 * labeled alternative in {@link CustomUrlParser#hostname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIPv6Host(CustomUrlParser.IPv6HostContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#v6host}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitV6host(CustomUrlParser.V6hostContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#port}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPort(CustomUrlParser.PortContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(CustomUrlParser.PathContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#user}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUser(CustomUrlParser.UserContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#login}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogin(CustomUrlParser.LoginContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#password}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassword(CustomUrlParser.PasswordContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#frag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrag(CustomUrlParser.FragContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(CustomUrlParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#search}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearch(CustomUrlParser.SearchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#searchparameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearchparameter(CustomUrlParser.SearchparameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CustomUrlParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(CustomUrlParser.StringContext ctx);
}