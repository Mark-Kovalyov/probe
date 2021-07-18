# Thrift

* Splittable: NO

## Sample of DSL

```thrift
namespace java mayton.geo.generated

enum Country {
 EN,
 FR,
 GE
}

struct GeoIpCity {
  1 : required i32 startIpNum,
  2 : required i32 endIpNum,
  3 : optional Country country,
  4 : optional string region,
  5 : optional string city,
  6 : optional string postalCode,
  7 : required double latitude,
  8 : required double longitude,
  9 : optional string dmaCode,
 10 : optional string areaCode
}

service GeoIpService {
    void store(1 : GeoIpCity geoIpCity)
    GeoIpCity retrieve(1 : i32 num)
}

```

## Datatypes

## Dependencies

```xml
        <dependency>
            <groupId>org.apache.thrift</groupId>
            <artifactId>libthrift</artifactId>
            <version>0.14.0</version>
        </dependency>
```

## CLI
```bash
Usage: thrift [options] file
Options:
  -version    Print the compiler version
  -o dir      Set the output directory for gen-* packages
               (default: current directory)
  -out dir    Set the ouput location for generated files.
               (no gen-* folder will be created)
  -I dir      Add a directory to the list of directories
                searched for include directives
  -nowarn     Suppress all compiler warnings (BAD!)
  -strict     Strict compiler warnings on
  -v[erbose]  Verbose mode
  -r[ecurse]  Also generate included files
  -debug      Parse debug trace to stdout
  --allow-neg-keys  Allow negative field keys (Used to preserve protocol
                compatibility with older .thrift files)
  --allow-64bit-consts  Do not print warnings about using 64-bit constants
  --gen STR   Generate code with a dynamically-registered generator.
                STR has the form language[:key1=val1[,key2[,key3=val3]]].
                Keys and values are options passed to the generator.
                Many options will not require values.

Options related to audit operation
   --audit OldFile   Old Thrift file to be audited with 'file'
  -Iold dir    Add a directory to the list of directories
                searched for include directives for old thrift file
  -Inew dir    Add a directory to the list of directories
                searched for include directives for new thrift file

Available generators (and options):
  as3 (AS3):
    bindable:        Add [bindable] metadata to all the struct classes.
  c_glib (C, using GLib):
  cl (Common Lisp):
    no_asd:          Do not define ASDF systems for each generated Thrift program.
    sys_pref=        The prefix to give ASDF system names. Default: thrift-gen-
  cpp (C++):
    cob_style:       Generate "Continuation OBject"-style classes.
    no_client_completion:
                     Omit calls to completion__() in CobClient class.
    no_default_operators:
                     Omits generation of default operators ==, != and <
    templates:       Generate templatized reader/writer methods.
    pure_enums:      Generate pure enums instead of wrapper classes.
    include_prefix:  Use full include paths in generated files.
    moveable_types:  Generate move constructors and assignment operators.
    no_ostream_operators:
                     Omit generation of ostream definitions.
    no_skeleton:     Omits generation of skeleton.
  csharp (C#):
    async:           Adds Async support using Task.Run.
    wcf:             Adds bindings for WCF to generated classes.
    serial:          Add serialization support to generated classes.
    nullable:        Use nullable types for properties.
    hashcode:        Generate a hashcode and equals implementation for classes.
    union:           Use new union typing, which includes a static read function for union types.
  d (D):
  dart (Dart):
    library_name:    Optional override for library name.
    library_prefix:  Generate code that can be used within an existing library.
                     Use a dot-separated string, e.g. "my_parent_lib.src.gen"
    pubspec_lib:     Optional override for thrift lib dependency in pubspec.yaml,
                     e.g. "thrift: 0.x.x".  Use a pipe delimiter to separate lines,
                     e.g. "thrift:|  git:|    url: git@foo.com"
  delphi (delphi):
    ansistr_binary:  Use AnsiString for binary datatype (default is TBytes).
    register_types:  Enable TypeRegistry, allows for creation of struct, union
                     and container instances by interface or TypeInfo()
    constprefix:     Name TConstants classes after IDL to reduce ambiguities
    events:          Enable and use processing events in the generated code.
    xmldoc:          Enable XMLDoc comments for Help Insight etc.
    async:           Generate IAsync interface to use Parallel Programming Library (XE7+ only).
  erl (Erlang):
    legacynames:     Output files retain naming conventions of Thrift 0.9.1 and earlier.
    maps:            Generate maps instead of dicts.
    otp16:           Generate non-namespaced dict and set instead of dict:dict and sets:set.
  go (Go):
    package_prefix=  Package prefix for generated files.
    thrift_import=   Override thrift package import path (default:github.com/apache/thrift/lib/go/thrift)
    package=         Package name (default: inferred from thrift file name)
    ignore_initialisms
                     Disable automatic spelling correction of initialisms (e.g. "URL")
    read_write_private
                     Make read/write methods private, default is public Read/Write
  gv (Graphviz):
    exceptions:      Whether to draw arrows from functions to exception.
  haxe (Haxe):
    callbacks        Use onError()/onSuccess() callbacks for service methods (like AS3)
    rtti             Enable @:rtti for generated classes and interfaces
    buildmacro=my.macros.Class.method(args)
                     Add @:build macro calls to generated classes and interfaces
  hs (Haskell):
  html (HTML):
    standalone:      Self-contained mode, includes all CSS in the HTML files.
                     Generates no style.css file, but HTML files will be larger.
    noescape:        Do not escape html in doc text.
  java (Java):
    beans:           Members will be private, and setter methods will return void.
    private-members: Members will be private, but setter methods will return 'this' like usual.
    nocamel:         Do not use CamelCase field accessors with beans.
    fullcamel:       Convert underscored_accessor_or_service_names to camelCase.
    android:         Generated structures are Parcelable.
    android_legacy:  Do not use java.io.IOException(throwable) (available for Android 2.3 and above).
    option_type:     Wrap optional fields in an Option type.
    rethrow_unhandled_exceptions:
                     Enable rethrow of unhandled exceptions and let them propagate futher. (Default behavior is to catch and log it.)
    java5:           Generate Java 1.5 compliant code (includes android_legacy flag).
    reuse-objects:   Data objects will not be allocated, but existing instances will be used (read and write).
    sorted_containers:
                     Use TreeSet/TreeMap instead of HashSet/HashMap as a implementation of set/map.
    generated_annotations=[undated|suppress]:
                     undated: suppress the date at @Generated annotations
                     suppress: suppress @Generated annotations entirely
    unsafe_binaries: Do not copy ByteBuffers in constructors, getters, and setters.
  javame (Java ME):
  js (Javascript):
    jquery:          Generate jQuery compatible code.
    node:            Generate node.js compatible code.
    ts:              Generate TypeScript definition files.
    with_ns:         Create global namespace objects when using node.js
    es6:             Create ES6 code with Promises
    thrift_package_output_directory=<path>:
                     Generate episode file and use the <path> as prefix
    imports=<paths_to_modules>:
                     ':' separated list of paths of modules that has episode files in their root
  json (JSON):
    merge:           Generate output with included files merged
  lua (Lua):
    omit_requires:   Suppress generation of require 'somefile'.
  netcore (C#):
    wcf:             Adds bindings for WCF to generated classes.
    serial:          Add serialization support to generated classes.
    nullable:        Use nullable types for properties.
    hashcode:        Generate a hashcode and equals implementation for classes.
    union:           Use new union typing, which includes a static read function for union types.
  netstd (C#):
    wcf:             Adds bindings for WCF to generated classes.
    serial:          Add serialization support to generated classes.
    union:           Use new union typing, which includes a static read function for union types.
  ocaml (OCaml):
  perl (Perl):
  php (PHP):
    inlined:         Generate PHP inlined files
    server:          Generate PHP server stubs
    oop:             Generate PHP with object oriented subclasses
    classmap:        Generate old-style PHP files (use classmap autoloading)
    rest:            Generate PHP REST processors
    nsglobal=NAME:   Set global namespace
    validate:        Generate PHP validator methods
    json:            Generate JsonSerializable classes (requires PHP >= 5.4)
  py (Python):
    zope.interface:  Generate code for use with zope.interface.
    twisted:         Generate Twisted-friendly RPC services.
    tornado:         Generate code for use with Tornado.
    no_utf8strings:  Do not Encode/decode strings using utf8 in the generated code. Basically no effect for Python 3.
    coding=CODING:   Add file encoding declare in generated file.
    slots:           Generate code using slots for instance members.
    dynamic:         Generate dynamic code, less code generated but slower.
    dynbase=CLS      Derive generated classes from class CLS instead of TBase.
    dynfrozen=CLS    Derive generated immutable classes from class CLS instead of TFrozenBase.
    dynexc=CLS       Derive generated exceptions from CLS instead of TExceptionBase.
    dynimport='from foo.bar import CLS'
                     Add an import line to generated code to find the dynbase class.
    package_prefix='top.package.'
                     Package prefix for generated files.
    old_style:       Deprecated. Generate old-style classes.
  rb (Ruby):
    rubygems:        Add a "require 'rubygems'" line to the top of each generated file.
    namespaced:      Generate files in idiomatic namespaced directories.
  rs (Rust):

  st (Smalltalk):
  swift (Swift 3.0):
    log_unexpected:  Log every time an unexpected field ID or type is encountered.
    debug_descriptions:
                     Allow use of debugDescription so the app can add description via a cateogory/extension
    async_clients:   Generate clients which invoke asynchronously via block syntax.
    namespaced:      Generate source in Module scoped output directories for Swift Namespacing.
    cocoa:           Generate Swift 2.x code compatible with the Thrift/Cocoa library
    promise_kit:     Generate clients which invoke asynchronously via promises (only use with cocoa flag)
    safe_enums:      Generate enum types with an unknown case to handle unspecified values rather than throw a serialization error
  xml (XML):
    merge:           Generate output with included files merged
    no_default_ns:   Omit default xmlns and add idl: prefix to all elements
    no_namespaces:   Do not add namespace definitions to the XML model
  xsd (XSD):
```

## Citatas

Thrift is pretty much same properties like protobuf. Also its not quite 
expressive.Thrift supports a lot more languages than protobuff. Thrift 
generates code to implement RPC clients and servers. Thrift has RPC support 
built in. Protobuf currently relies on third-party libraries, meaning less eyes, 
less testing, less reliable code.This is a good format but since its not splittable, 
it is not recommended for Hadoop.