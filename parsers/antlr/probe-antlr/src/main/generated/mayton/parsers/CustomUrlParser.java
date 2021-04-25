// Generated from CustomUrl.g4 by ANTLR 4.9.2

    package mayton.parsers;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CustomUrlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, DIGITS=13, HEX=14, STRING=15, WS=16;
	public static final int
		RULE_prog = 0, RULE_url = 1, RULE_uri = 2, RULE_scheme = 3, RULE_host = 4, 
		RULE_hostname = 5, RULE_v6host = 6, RULE_port = 7, RULE_path = 8, RULE_user = 9, 
		RULE_login = 10, RULE_password = 11, RULE_frag = 12, RULE_query = 13, 
		RULE_search = 14, RULE_searchparameter = 15, RULE_string = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "url", "uri", "scheme", "host", "hostname", "v6host", "port", 
			"path", "user", "login", "password", "frag", "query", "search", "searchparameter", 
			"string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'://'", "':'", "'/'", "'.'", "'['", "']'", "'::'", "'@'", "'#'", 
			"'?'", "'&'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "DIGITS", "HEX", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CustomUrl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CustomUrlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public UrlContext url() {
			return getRuleContext(UrlContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			url();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UrlContext extends ParserRuleContext {
		public UriContext uri() {
			return getRuleContext(UriContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CustomUrlParser.EOF, 0); }
		public UrlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_url; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterUrl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitUrl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitUrl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UrlContext url() throws RecognitionException {
		UrlContext _localctx = new UrlContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_url);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			uri();
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UriContext extends ParserRuleContext {
		public SchemeContext scheme() {
			return getRuleContext(SchemeContext.class,0);
		}
		public HostContext host() {
			return getRuleContext(HostContext.class,0);
		}
		public LoginContext login() {
			return getRuleContext(LoginContext.class,0);
		}
		public PortContext port() {
			return getRuleContext(PortContext.class,0);
		}
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public FragContext frag() {
			return getRuleContext(FragContext.class,0);
		}
		public TerminalNode WS() { return getToken(CustomUrlParser.WS, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public UriContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uri; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterUri(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitUri(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitUri(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UriContext uri() throws RecognitionException {
		UriContext _localctx = new UriContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_uri);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			scheme();
			setState(40);
			match(T__0);
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(41);
				login();
				}
				break;
			}
			setState(44);
			host();
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(45);
				match(T__1);
				setState(46);
				port();
				}
			}

			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(49);
				match(T__2);
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(50);
					path();
					}
				}

				}
			}

			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(55);
				query();
				}
			}

			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(58);
				frag();
				}
			}

			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(61);
				match(WS);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchemeContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public SchemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scheme; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterScheme(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitScheme(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitScheme(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemeContext scheme() throws RecognitionException {
		SchemeContext _localctx = new SchemeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_scheme);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			string();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HostContext extends ParserRuleContext {
		public HostnameContext hostname() {
			return getRuleContext(HostnameContext.class,0);
		}
		public HostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_host; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterHost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitHost(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitHost(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HostContext host() throws RecognitionException {
		HostContext _localctx = new HostContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_host);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(66);
				match(T__2);
				}
			}

			setState(69);
			hostname();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HostnameContext extends ParserRuleContext {
		public HostnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hostname; }
	 
		public HostnameContext() { }
		public void copyFrom(HostnameContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IPv6HostContext extends HostnameContext {
		public V6hostContext v6host() {
			return getRuleContext(V6hostContext.class,0);
		}
		public IPv6HostContext(HostnameContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterIPv6Host(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitIPv6Host(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitIPv6Host(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DomainNameOrIPv4HostContext extends HostnameContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public DomainNameOrIPv4HostContext(HostnameContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterDomainNameOrIPv4Host(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitDomainNameOrIPv4Host(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitDomainNameOrIPv4Host(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HostnameContext hostname() throws RecognitionException {
		HostnameContext _localctx = new HostnameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_hostname);
		int _la;
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new DomainNameOrIPv4HostContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				string();
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(72);
					match(T__3);
					setState(73);
					string();
					}
					}
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__4:
				_localctx = new IPv6HostContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(T__4);
				setState(80);
				v6host();
				setState(81);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class V6hostContext extends ParserRuleContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public List<TerminalNode> DIGITS() { return getTokens(CustomUrlParser.DIGITS); }
		public TerminalNode DIGITS(int i) {
			return getToken(CustomUrlParser.DIGITS, i);
		}
		public V6hostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_v6host; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterV6host(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitV6host(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitV6host(this);
			else return visitor.visitChildren(this);
		}
	}

	public final V6hostContext v6host() throws RecognitionException {
		V6hostContext _localctx = new V6hostContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_v6host);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(85);
				match(T__6);
				}
			}

			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(88);
				string();
				}
				break;
			case DIGITS:
				{
				setState(89);
				match(DIGITS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==T__6) {
				{
				{
				setState(92);
				_la = _input.LA(1);
				if ( !(_la==T__1 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(95);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRING:
					{
					setState(93);
					string();
					}
					break;
				case DIGITS:
					{
					setState(94);
					match(DIGITS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PortContext extends ParserRuleContext {
		public TerminalNode DIGITS() { return getToken(CustomUrlParser.DIGITS, 0); }
		public PortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_port; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterPort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitPort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitPort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PortContext port() throws RecognitionException {
		PortContext _localctx = new PortContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_port);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(DIGITS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathContext extends ParserRuleContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_path);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			string();
			setState(109);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(105);
					match(T__2);
					setState(106);
					string();
					}
					} 
				}
				setState(111);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(112);
				match(T__2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public UserContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_user; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterUser(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitUser(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitUser(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserContext user() throws RecognitionException {
		UserContext _localctx = new UserContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_user);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			string();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoginContext extends ParserRuleContext {
		public UserContext user() {
			return getRuleContext(UserContext.class,0);
		}
		public PasswordContext password() {
			return getRuleContext(PasswordContext.class,0);
		}
		public LoginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_login; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterLogin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitLogin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitLogin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoginContext login() throws RecognitionException {
		LoginContext _localctx = new LoginContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_login);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			user();
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(118);
				match(T__1);
				setState(119);
				password();
				}
			}

			setState(122);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PasswordContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public PasswordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_password; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterPassword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitPassword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitPassword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PasswordContext password() throws RecognitionException {
		PasswordContext _localctx = new PasswordContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_password);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			string();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FragContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode DIGITS() { return getToken(CustomUrlParser.DIGITS, 0); }
		public FragContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_frag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterFrag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitFrag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitFrag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FragContext frag() throws RecognitionException {
		FragContext _localctx = new FragContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_frag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__8);
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(127);
				string();
				}
				break;
			case DIGITS:
				{
				setState(128);
				match(DIGITS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public SearchContext search() {
			return getRuleContext(SearchContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__9);
			setState(132);
			search();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SearchContext extends ParserRuleContext {
		public List<SearchparameterContext> searchparameter() {
			return getRuleContexts(SearchparameterContext.class);
		}
		public SearchparameterContext searchparameter(int i) {
			return getRuleContext(SearchparameterContext.class,i);
		}
		public SearchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_search; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterSearch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitSearch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitSearch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SearchContext search() throws RecognitionException {
		SearchContext _localctx = new SearchContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_search);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			searchparameter();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(135);
				match(T__10);
				setState(136);
				searchparameter();
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SearchparameterContext extends ParserRuleContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public TerminalNode DIGITS() { return getToken(CustomUrlParser.DIGITS, 0); }
		public TerminalNode HEX() { return getToken(CustomUrlParser.HEX, 0); }
		public SearchparameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_searchparameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterSearchparameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitSearchparameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitSearchparameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SearchparameterContext searchparameter() throws RecognitionException {
		SearchparameterContext _localctx = new SearchparameterContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_searchparameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			string();
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(143);
				match(T__11);
				setState(147);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRING:
					{
					setState(144);
					string();
					}
					break;
				case DIGITS:
					{
					setState(145);
					match(DIGITS);
					}
					break;
				case HEX:
					{
					setState(146);
					match(HEX);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CustomUrlParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CustomUrlListener ) ((CustomUrlListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CustomUrlVisitor ) return ((CustomUrlVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22\u009c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\5\4-\n\4\3\4\3\4\3\4\5\4\62\n\4\3\4\3"+
		"\4\5\4\66\n\4\5\48\n\4\3\4\5\4;\n\4\3\4\5\4>\n\4\3\4\5\4A\n\4\3\5\3\5"+
		"\3\6\5\6F\n\6\3\6\3\6\3\7\3\7\3\7\7\7M\n\7\f\7\16\7P\13\7\3\7\3\7\3\7"+
		"\3\7\5\7V\n\7\3\b\5\bY\n\b\3\b\3\b\5\b]\n\b\3\b\3\b\3\b\5\bb\n\b\7\bd"+
		"\n\b\f\b\16\bg\13\b\3\t\3\t\3\n\3\n\3\n\7\nn\n\n\f\n\16\nq\13\n\3\n\5"+
		"\nt\n\n\3\13\3\13\3\f\3\f\3\f\5\f{\n\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\5\16\u0084\n\16\3\17\3\17\3\17\3\20\3\20\3\20\7\20\u008c\n\20\f\20\16"+
		"\20\u008f\13\20\3\21\3\21\3\21\3\21\3\21\5\21\u0096\n\21\5\21\u0098\n"+
		"\21\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2"+
		"\3\4\2\4\4\t\t\2\u00a0\2$\3\2\2\2\4&\3\2\2\2\6)\3\2\2\2\bB\3\2\2\2\nE"+
		"\3\2\2\2\fU\3\2\2\2\16X\3\2\2\2\20h\3\2\2\2\22j\3\2\2\2\24u\3\2\2\2\26"+
		"w\3\2\2\2\30~\3\2\2\2\32\u0080\3\2\2\2\34\u0085\3\2\2\2\36\u0088\3\2\2"+
		"\2 \u0090\3\2\2\2\"\u0099\3\2\2\2$%\5\4\3\2%\3\3\2\2\2&\'\5\6\4\2\'(\7"+
		"\2\2\3(\5\3\2\2\2)*\5\b\5\2*,\7\3\2\2+-\5\26\f\2,+\3\2\2\2,-\3\2\2\2-"+
		".\3\2\2\2.\61\5\n\6\2/\60\7\4\2\2\60\62\5\20\t\2\61/\3\2\2\2\61\62\3\2"+
		"\2\2\62\67\3\2\2\2\63\65\7\5\2\2\64\66\5\22\n\2\65\64\3\2\2\2\65\66\3"+
		"\2\2\2\668\3\2\2\2\67\63\3\2\2\2\678\3\2\2\28:\3\2\2\29;\5\34\17\2:9\3"+
		"\2\2\2:;\3\2\2\2;=\3\2\2\2<>\5\32\16\2=<\3\2\2\2=>\3\2\2\2>@\3\2\2\2?"+
		"A\7\22\2\2@?\3\2\2\2@A\3\2\2\2A\7\3\2\2\2BC\5\"\22\2C\t\3\2\2\2DF\7\5"+
		"\2\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\5\f\7\2H\13\3\2\2\2IN\5\"\22\2JK"+
		"\7\6\2\2KM\5\"\22\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OV\3\2\2\2"+
		"PN\3\2\2\2QR\7\7\2\2RS\5\16\b\2ST\7\b\2\2TV\3\2\2\2UI\3\2\2\2UQ\3\2\2"+
		"\2V\r\3\2\2\2WY\7\t\2\2XW\3\2\2\2XY\3\2\2\2Y\\\3\2\2\2Z]\5\"\22\2[]\7"+
		"\17\2\2\\Z\3\2\2\2\\[\3\2\2\2]e\3\2\2\2^a\t\2\2\2_b\5\"\22\2`b\7\17\2"+
		"\2a_\3\2\2\2a`\3\2\2\2bd\3\2\2\2c^\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2"+
		"\2f\17\3\2\2\2ge\3\2\2\2hi\7\17\2\2i\21\3\2\2\2jo\5\"\22\2kl\7\5\2\2l"+
		"n\5\"\22\2mk\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2ps\3\2\2\2qo\3\2\2\2"+
		"rt\7\5\2\2sr\3\2\2\2st\3\2\2\2t\23\3\2\2\2uv\5\"\22\2v\25\3\2\2\2wz\5"+
		"\24\13\2xy\7\4\2\2y{\5\30\r\2zx\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7\n\2\2"+
		"}\27\3\2\2\2~\177\5\"\22\2\177\31\3\2\2\2\u0080\u0083\7\13\2\2\u0081\u0084"+
		"\5\"\22\2\u0082\u0084\7\17\2\2\u0083\u0081\3\2\2\2\u0083\u0082\3\2\2\2"+
		"\u0084\33\3\2\2\2\u0085\u0086\7\f\2\2\u0086\u0087\5\36\20\2\u0087\35\3"+
		"\2\2\2\u0088\u008d\5 \21\2\u0089\u008a\7\r\2\2\u008a\u008c\5 \21\2\u008b"+
		"\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\37\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0097\5\"\22\2\u0091\u0095"+
		"\7\16\2\2\u0092\u0096\5\"\22\2\u0093\u0096\7\17\2\2\u0094\u0096\7\20\2"+
		"\2\u0095\u0092\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\u0098"+
		"\3\2\2\2\u0097\u0091\3\2\2\2\u0097\u0098\3\2\2\2\u0098!\3\2\2\2\u0099"+
		"\u009a\7\21\2\2\u009a#\3\2\2\2\27,\61\65\67:=@ENUX\\aeosz\u0083\u008d"+
		"\u0095\u0097";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}