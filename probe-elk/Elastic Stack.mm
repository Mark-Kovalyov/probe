<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1556017414564" ID="ID_1971181355" MODIFIED="1557130090004" TEXT="Elastic Stack">
<node CREATED="1556017487320" ID="ID_487288409" MODIFIED="1556017565952" POSITION="right" TEXT="FTS algorithms">
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1556017522577" ID="ID_816567218" MODIFIED="1556017568539" POSITION="left" TEXT="Collect, Parse, Transform with Logstash">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1556017538065" ID="ID_1880268680" MODIFIED="1556017574116" POSITION="right" TEXT="Kibana">
<icon BUILTIN="full-3"/>
</node>
<node CREATED="1556017543209" ID="ID_290395491" MODIFIED="1556017577364" POSITION="left" TEXT="Building Data Pipeline">
<icon BUILTIN="full-4"/>
</node>
<node CREATED="1556017683441" ID="ID_1316147149" MODIFIED="1556018147387" POSITION="right" TEXT="products">
<icon BUILTIN="messagebox_warning"/>
<node CREATED="1556017687793" ID="ID_1686178696" MODIFIED="1556017695997" TEXT="Elastic Search">
<node CREATED="1556017698417" ID="ID_315171795" MODIFIED="1556017701293" TEXT="Apache Lucene"/>
<node CREATED="1556017705360" ID="ID_1208697787" MODIFIED="1556017710357" TEXT="FTS engine"/>
</node>
<node CREATED="1556017740151" ID="ID_1889459942" MODIFIED="1556017742724" TEXT="Logstash"/>
<node CREATED="1556017770225" ID="ID_1738510021" MODIFIED="1556017777149" TEXT="Logstash Forwarder"/>
<node CREATED="1556017803448" FOLDED="true" ID="ID_455469855" MODIFIED="1563811281703" TEXT="Kibana">
<node CREATED="1556017810760" FOLDED="true" ID="ID_930375395" MODIFIED="1556017824709" TEXT="browser based dashboard">
<node CREATED="1556017816473" ID="ID_67471126" MODIFIED="1556017816473" TEXT=""/>
</node>
</node>
<node CREATED="1556017876224" ID="ID_1023318586" MODIFIED="1556017878566" TEXT="Beats">
<node CREATED="1556017950904" ID="ID_1579836981" MODIFIED="1556017959917" TEXT="real-time network packet analyzer"/>
</node>
<node CREATED="1556017892760" ID="ID_887188733" MODIFIED="1556017894517" TEXT="Xpack">
<node CREATED="1556017901441" ID="ID_1076356121" MODIFIED="1556017904093" TEXT="security"/>
<node CREATED="1556017905017" ID="ID_414505063" MODIFIED="1556017907725" TEXT="alerting"/>
<node CREATED="1556017908673" ID="ID_1172252274" MODIFIED="1556017911989" TEXT="monitoring"/>
</node>
<node CREATED="1556017988408" ID="ID_961912676" MODIFIED="1556017995950" TEXT="Winlogbeats"/>
<node CREATED="1556018035592" ID="ID_1964928983" MODIFIED="1556018040004" TEXT="Shield"/>
<node CREATED="1556018040945" ID="ID_364895892" MODIFIED="1556018043253" TEXT="Marvel"/>
<node CREATED="1556018110744" ID="ID_1054869131" MODIFIED="1556018114213" TEXT="Watcher"/>
</node>
<node CREATED="1556018194754" ID="ID_1841678153" MODIFIED="1556018209621" POSITION="left" TEXT="Making Sence of your data">
<node CREATED="1556018273137" ID="ID_1680784031" MODIFIED="1556018278005" TEXT="Search"/>
<node CREATED="1556018283418" ID="ID_1677588211" MODIFIED="1556018286301" TEXT="Suggestions"/>
<node CREATED="1556018301328" ID="ID_658161834" MODIFIED="1556018306685" TEXT="Log Analysis">
<node CREATED="1556018314400" ID="ID_1593510686" MODIFIED="1556018318581" TEXT="Issue debugging"/>
<node CREATED="1556018343793" ID="ID_1674951690" MODIFIED="1556018396053" TEXT="Security analysis"/>
<node CREATED="1556018363352" ID="ID_1810221182" MODIFIED="1556018392365" TEXT="Performance analysis"/>
<node CREATED="1556018382521" ID="ID_605128996" MODIFIED="1556018388613" TEXT="Predictive analysis"/>
</node>
</node>
<node CREATED="1556018556106" FOLDED="true" ID="ID_1471447414" MODIFIED="1556018797662" POSITION="right" TEXT="Elastic config">
<node CREATED="1556018562232" ID="ID_1859831983" MODIFIED="1556018571221" TEXT="elasticsearch.yml">
<node CREATED="1556018583658" ID="ID_1167418873" MODIFIED="1556018602045" TEXT="cluster.name : Packt"/>
<node CREATED="1556018603872" ID="ID_685745417" MODIFIED="1556018611173" TEXT="node.name : Packtnode"/>
<node CREATED="1556018613985" ID="ID_1708707506" MODIFIED="1556018623173" TEXT="http.port: 9200"/>
</node>
<node CREATED="1556018645121" ID="ID_1907493459" MODIFIED="1556018646765" TEXT="bin">
<node CREATED="1556018690250" ID="ID_1833418163" MODIFIED="1556018703277" TEXT="elasticsearch-service.sh">
<node CREATED="1556018710354" ID="ID_1681371893" MODIFIED="1556018712550" TEXT="start"/>
</node>
</node>
</node>
<node CREATED="1556018778410" ID="ID_1856208767" MODIFIED="1556018803693" POSITION="left" TEXT="LogStash config">
<node CREATED="1556018812737" ID="ID_641891538" MODIFIED="1556018815845" TEXT="NSSM">
<node CREATED="1556018816593" ID="ID_1836516700" MODIFIED="1556019065485" TEXT="NonSuckingServManager"/>
</node>
<node CREATED="1556019096904" ID="ID_1979014927" MODIFIED="1556019105093" TEXT="logstash">
<node CREATED="1556019107394" ID="ID_1801075978" MODIFIED="1556019108949" TEXT="bin">
<node CREATED="1556019111610" ID="ID_122483948" MODIFIED="1556019119044" TEXT="LogstashPipeLine.conf">
<node CREATED="1556019126623" ID="ID_793097" MODIFIED="1556019155567" TEXT="input file path =&gt; &quot;&quot;"/>
<node CREATED="1556019167145" ID="ID_1585647582" MODIFIED="1556019177133" TEXT="output file path =&gt; &quot;&quot;"/>
</node>
<node CREATED="1556023550094" ID="ID_1211843024" MODIFIED="1556023553355" TEXT="logstash">
<node CREATED="1556023554059" ID="ID_1182828966" MODIFIED="1556023568155" TEXT="-f LogStashPipeline.conf"/>
<node CREATED="1556023572906" ID="ID_1165794901" MODIFIED="1556023595098" TEXT="--config.test"/>
</node>
</node>
</node>
</node>
<node CREATED="1556024087136" ID="ID_1640302993" MODIFIED="1556209196075" POSITION="right" TEXT="Kibana">
<node CREATED="1556024135930" ID="ID_455862878" MODIFIED="1556024144195" TEXT="http://localhost:5600"/>
<node CREATED="1556029913068" ID="ID_756860487" MODIFIED="1556029924736" TEXT="GET _cluster/health"/>
<node CREATED="1556029964797" ID="ID_586497830" MODIFIED="1556029988680" TEXT="PUT movies {...}"/>
<node CREATED="1556208058667" ID="ID_424655514" MODIFIED="1556208073185" TEXT="Finding Insights With Kibana">
<node CREATED="1556208702226" ID="ID_791610391" MODIFIED="1556208712222" TEXT="Configure an index pattern"/>
<node CREATED="1556208836577" ID="ID_639928807" MODIFIED="1556208838836" TEXT="Discover">
<node CREATED="1556208840376" ID="ID_904286792" MODIFIED="1556208853462" TEXT="employee_number:&gt;10 AND sal:&gt;45"/>
</node>
</node>
<node CREATED="1556209196075" ID="ID_431652193" MODIFIED="1556209198651" TEXT="visualize">
<node CREATED="1556209277009" ID="ID_1951424734" MODIFIED="1556209280188" TEXT="piechart"/>
<node CREATED="1556209344866" ID="ID_683656477" MODIFIED="1556209347636" TEXT="linechart">
<node CREATED="1556209349496" ID="ID_1047884278" MODIFIED="1556209356402" TEXT="Y-Axes"/>
<node CREATED="1556209358870" ID="ID_671925896" MODIFIED="1556209361794" TEXT="X-AXes"/>
</node>
<node CREATED="1556292350787" ID="ID_1660824430" MODIFIED="1556292359721" TEXT="metrics">
<node CREATED="1556292367542" ID="ID_374229414" MODIFIED="1556292390337" TEXT="buckets"/>
</node>
</node>
<node CREATED="1556292478470" ID="ID_990537363" MODIFIED="1556292563954" TEXT="dashboards">
<node CREATED="1556292528349" ID="ID_171872778" MODIFIED="1556292529850" TEXT="add"/>
</node>
<node CREATED="1556295629662" ID="ID_70451224" MODIFIED="1556295632754" TEXT="Kibana 5">
<node CREATED="1556295635197" ID="ID_336550613" MODIFIED="1556295637041" TEXT="Console"/>
<node CREATED="1556295638277" ID="ID_1678484537" MODIFIED="1556295640704" TEXT="Timelion">
<node CREATED="1556295666469" ID="ID_64395988" MODIFIED="1556295670369" TEXT="Functions">
<node CREATED="1556295673629" ID="ID_36831238" MODIFIED="1556295677080" TEXT="sum"/>
<node CREATED="1556295678548" ID="ID_1465556039" MODIFIED="1556295680096" TEXT="avg"/>
<node CREATED="1556295703781" ID="ID_1390427709" MODIFIED="1556295705945" TEXT="es"/>
</node>
<node CREATED="1556297066157" ID="ID_914577260" MODIFIED="1556297069582" TEXT="TimeRange">
<node CREATED="1556297070371" ID="ID_955346883" MODIFIED="1556297075415" TEXT="Quick"/>
<node CREATED="1556297076484" ID="ID_1938427838" MODIFIED="1556297079222" TEXT="Relative"/>
<node CREATED="1556297080828" ID="ID_1528947255" MODIFIED="1556297083326" TEXT="Absolute"/>
</node>
</node>
<node CREATED="1556295643244" ID="ID_1465936874" MODIFIED="1556295646216" TEXT="New design"/>
<node CREATED="1556297339867" ID="ID_619602259" MODIFIED="1556297342181" TEXT="Tag cloud"/>
</node>
<node CREATED="1556297334465" ID="ID_380145565" MODIFIED="1556297334465" TEXT=""/>
</node>
<node CREATED="1556028657024" ID="ID_1946231752" MODIFIED="1556028672838" POSITION="left" TEXT="terminology">
<node CREATED="1556028662990" ID="ID_402569560" MODIFIED="1556028665697" TEXT="Cluster"/>
<node CREATED="1556028672838" ID="ID_1291624924" MODIFIED="1556028675897" TEXT="Nodes">
<node CREATED="1556028929525" ID="ID_935893098" MODIFIED="1556028933049" TEXT="Master"/>
<node CREATED="1556028934101" ID="ID_457069145" MODIFIED="1556028939378" TEXT="Master Eligible"/>
<node CREATED="1556029001364" ID="ID_1165023814" MODIFIED="1556029005009" TEXT="Data node"/>
<node CREATED="1556029014636" ID="ID_624630590" MODIFIED="1556029018657" TEXT="Ingest node"/>
<node CREATED="1556029057620" ID="ID_914588632" MODIFIED="1556029065353" TEXT="Tribe node"/>
</node>
<node CREATED="1556028676653" ID="ID_725552202" MODIFIED="1556028678025" TEXT="Index"/>
<node CREATED="1556028684717" ID="ID_1059412127" MODIFIED="1556028686745" TEXT="Type"/>
<node CREATED="1556028687669" ID="ID_422010391" MODIFIED="1556028690347" TEXT="Document"/>
<node CREATED="1556028691606" ID="ID_1775852359" MODIFIED="1556028697529" TEXT="Shards and Replicas"/>
</node>
<node CREATED="1556029613431" ID="ID_1783262907" MODIFIED="1556029619282" POSITION="right" TEXT="REST APIS">
<node CREATED="1556029624685" ID="ID_506206246" MODIFIED="1556029629760" TEXT="Cluster API"/>
<node CREATED="1556029630772" ID="ID_1048874705" MODIFIED="1556029636161" TEXT="Indices API"/>
<node CREATED="1556029637196" ID="ID_1077050379" MODIFIED="1556029641033" TEXT="Document API"/>
<node CREATED="1556029642236" ID="ID_1328431675" MODIFIED="1556029644337" TEXT="CAT API"/>
<node CREATED="1556029660461" ID="ID_1535334659" MODIFIED="1556029665849" TEXT="Search API"/>
</node>
<node CREATED="1556095803448" ID="ID_1854601808" MODIFIED="1556095806587" POSITION="left" TEXT="Query DSL">
<node CREATED="1556095808567" ID="ID_114067149" MODIFIED="1556095811322" TEXT="Query">
<node CREATED="1556095817880" ID="ID_801204628" MODIFIED="1556095833026" TEXT="FTS, relevance, not cacheble, slow"/>
</node>
<node CREATED="1556095812374" ID="ID_781450461" MODIFIED="1556095814203" TEXT="Filters">
<node CREATED="1556095835239" ID="ID_1763044995" MODIFIED="1556095851764" TEXT="boolean, exact match, cacheble, perf"/>
</node>
<node CREATED="1556095901110" ID="ID_1650199263" MODIFIED="1556095905210" TEXT="Clauses">
<node CREATED="1556095905988" ID="ID_449009463" MODIFIED="1556095908905" TEXT="Leaf"/>
<node CREATED="1556095909966" ID="ID_1106103905" MODIFIED="1556095912649" TEXT="Compound"/>
</node>
<node CREATED="1556095935118" ID="ID_351910669" MODIFIED="1556096792511" TEXT="Type of query">
<node CREATED="1556096389314" ID="ID_654118641" MODIFIED="1556096392294" TEXT="match_all"/>
<node CREATED="1556096393777" ID="ID_740761380" MODIFIED="1556096399733" TEXT="match_phrase_prefix"/>
<node CREATED="1556096400993" ID="ID_845003971" MODIFIED="1556096402965" TEXT="wildcard"/>
<node CREATED="1556096420777" ID="ID_101996514" MODIFIED="1556096424742" TEXT="multi_match"/>
<node CREATED="1556096634175" ID="ID_111294330" MODIFIED="1556096636075" TEXT="range">
<node CREATED="1556096802150" ID="ID_772970834" MODIFIED="1556096804362" TEXT="lt"/>
<node CREATED="1556096805063" ID="ID_645125555" MODIFIED="1556096806836" TEXT="gt"/>
</node>
<node CREATED="1556096794046" ID="ID_1799712020" MODIFIED="1556096798130" TEXT="aggs">
<node CREATED="1556096808711" ID="ID_1285805478" MODIFIED="1556096810914" TEXT="avg"/>
<node CREATED="1556112756820" ID="ID_275218296" MODIFIED="1556112756820" TEXT=""/>
</node>
</node>
</node>
<node CREATED="1556113155034" ID="ID_1269277044" MODIFIED="1556113158733" POSITION="right" TEXT="aggregation">
<node CREATED="1556113170728" ID="ID_858253733" MODIFIED="1556113173350" TEXT="metrics"/>
<node CREATED="1556113179474" ID="ID_1011671647" MODIFIED="1556113181405" TEXT="bucket"/>
<node CREATED="1556113201577" ID="ID_902517263" MODIFIED="1556113207965" TEXT="matrix"/>
<node CREATED="1556113218139" ID="ID_787944043" MODIFIED="1556113220725" TEXT="pipeline"/>
</node>
<node CREATED="1556116936666" ID="ID_813485325" MODIFIED="1556116940273" POSITION="left" TEXT="Analyzers">
<node CREATED="1556117392634" ID="ID_439559535" MODIFIED="1556117401502" TEXT="stop"/>
<node CREATED="1556117402539" ID="ID_101008543" MODIFIED="1556117405735" TEXT="whitespace"/>
</node>
<node CREATED="1556117442794" ID="ID_256201379" MODIFIED="1556117504673" POSITION="right" TEXT="scripting">
<node CREATED="1556117466533" ID="ID_809713930" MODIFIED="1556117469543" TEXT="languages">
<node CREATED="1556117470498" ID="ID_240151199" MODIFIED="1556117470498" TEXT=""/>
<node CREATED="1556117483454" ID="ID_681350925" MODIFIED="1556117486942" TEXT="painless"/>
<node CREATED="1556117488082" ID="ID_810623986" MODIFIED="1556117491646" TEXT="JavaScript"/>
<node CREATED="1556117492621" ID="ID_1687032978" MODIFIED="1556117496719" TEXT="Python"/>
<node CREATED="1556117497931" ID="ID_997510907" MODIFIED="1556117499567" TEXT="Groove"/>
</node>
<node CREATED="1556117504674" ID="ID_1951616940" MODIFIED="1556117506552" TEXT="special">
<node CREATED="1556117515197" ID="ID_1407598694" MODIFIED="1556117522182" TEXT="Lucene Expressions"/>
<node CREATED="1556117523010" ID="ID_910394828" MODIFIED="1556117527519" TEXT="Mustache"/>
<node CREATED="1556117528163" ID="ID_1032167114" MODIFIED="1556117529398" TEXT="Java"/>
</node>
</node>
<node CREATED="1556202403158" ID="ID_987460637" MODIFIED="1556202406609" POSITION="left" TEXT="LogStash">
<node CREATED="1556202415116" ID="ID_1172354199" MODIFIED="1556202417648" TEXT="Stashing">
<node CREATED="1556202429575" ID="ID_973682325" MODIFIED="1556202435769" TEXT="Pipeline structure">
<node CREATED="1556202443181" ID="ID_150092760" MODIFIED="1556202445936" TEXT="input">
<node CREATED="1556298039277" ID="ID_894731619" MODIFIED="1556298041033" TEXT="path"/>
</node>
<node CREATED="1556202447205" ID="ID_973019647" MODIFIED="1556202449105" TEXT="filter">
<node CREATED="1556298014766" ID="ID_1965995752" MODIFIED="1556298016266" TEXT="grok">
<node CREATED="1556298016943" ID="ID_497712702" MODIFIED="1556298018338" TEXT="match"/>
</node>
</node>
<node CREATED="1556202450196" ID="ID_883660982" MODIFIED="1556202451785" TEXT="output"/>
</node>
</node>
<node CREATED="1556202800795" ID="ID_177268657" MODIFIED="1556202802599" TEXT="config">
<node CREATED="1556202812196" ID="ID_1933192116" MODIFIED="1556202823294" TEXT="LogStashPipelineCSV.conf"/>
</node>
<node CREATED="1556207219965" ID="ID_686881111" MODIFIED="1556207223021" TEXT="Plugins">
<node CREATED="1556207237648" ID="ID_1025585698" MODIFIED="1556207241571" TEXT="input plugin">
<node CREATED="1556207251174" ID="ID_1610485848" MODIFIED="1556207253644" TEXT="github"/>
<node CREATED="1556207258511" ID="ID_1417071815" MODIFIED="1556207261123" TEXT="kafka"/>
<node CREATED="1556207264081" ID="ID_674428461" MODIFIED="1556207265220" TEXT="jdbc"/>
<node CREATED="1556207269376" ID="ID_647321261" MODIFIED="1556207273508" TEXT="amazon sq"/>
</node>
<node CREATED="1556207277240" HGAP="94" ID="ID_509616156" MODIFIED="1557133621293" TEXT="output plugin" VSHIFT="33">
<node CREATED="1556207285936" ID="ID_1370646515" MODIFIED="1556207289459" TEXT="jira"/>
<node CREATED="1556207290432" ID="ID_1509158466" MODIFIED="1556207292411" TEXT="logdb"/>
<node CREATED="1556207293452" ID="ID_308214957" MODIFIED="1556207295371" TEXT="elastic"/>
<node CREATED="1556207296944" ID="ID_1610077715" MODIFIED="1556207298338" TEXT="redis"/>
</node>
<node CREATED="1556207301680" HGAP="114" ID="ID_71811699" MODIFIED="1557133614060" TEXT="filter plugin" VSHIFT="-49">
<node CREATED="1556207310262" ID="ID_920747349" MODIFIED="1556207311115" TEXT="csv"/>
<node CREATED="1556207312935" ID="ID_1004894924" MODIFIED="1556207315372" TEXT="grok"/>
<node CREATED="1556207319527" ID="ID_554706214" MODIFIED="1556207321323" TEXT="json"/>
<node CREATED="1556207323551" ID="ID_1232282243" MODIFIED="1556207325234" TEXT="yaml"/>
<node CREATED="1556207331393" ID="ID_1779452340" MODIFIED="1556207340003" TEXT="avro"/>
</node>
</node>
<node CREATED="1556207355185" ID="ID_1501439252" MODIFIED="1556207357044" TEXT="API">
<node CREATED="1556207956215" ID="ID_1688217511" MODIFIED="1556207959474" TEXT="Node Info"/>
<node CREATED="1556207960989" ID="ID_1475248422" MODIFIED="1556207965785" TEXT="Plugins Info"/>
<node CREATED="1556207967204" ID="ID_433602972" MODIFIED="1556207970313" TEXT="Node Stats"/>
<node CREATED="1556207971982" ID="ID_338552884" MODIFIED="1556207976289" TEXT="Hot Threads"/>
</node>
</node>
<node CREATED="1556297348554" ID="ID_1298908658" MODIFIED="1556297352542" POSITION="right" TEXT="Data Pipeline"/>
<node CREATED="1557130091063" ID="ID_605040205" MODIFIED="1557130095047" POSITION="left" TEXT="site search"/>
<node CREATED="1557131516476" ID="ID_1317339202" MODIFIED="1557131522270" POSITION="right" TEXT="Security Analyzis">
<node CREATED="1557131527322" ID="ID_1605448775" MODIFIED="1557131532143" TEXT="auth logs"/>
<node CREATED="1557131533209" ID="ID_1978231401" MODIFIED="1557131541142" TEXT="NetFlow records"/>
<node CREATED="1557131542050" ID="ID_920460369" MODIFIED="1557131545359" TEXT="DNS protocol"/>
<node CREATED="1557131592843" ID="ID_1050999485" MODIFIED="1557131605815" TEXT="Filebeat, Autitbeat, PacketBeat"/>
</node>
<node CREATED="1557133112177" ID="ID_1794359594" MODIFIED="1557133125750" POSITION="left" TEXT="Application Monitoring (APM)">
<node CREATED="1557133133226" ID="ID_950158930" MODIFIED="1557133139006" TEXT="NodeJS, Python">
<node CREATED="1557133193329" ID="ID_590105206" MODIFIED="1557133206748" TEXT="require(&apos;elastoc-apm-node&apos;)"/>
</node>
<node CREATED="1557133140137" ID="ID_1770706199" MODIFIED="1557133160421" TEXT="Logs  fpr response time, errors"/>
</node>
</node>
</map>
