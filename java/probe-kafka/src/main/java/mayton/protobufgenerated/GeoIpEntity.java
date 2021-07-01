package mayton.protobufgenerated;

import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;

public final class GeoIpEntity {

    private GeoIpEntity() {
    }

    public static void registerAllExtensions(
            ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            ExtensionRegistry registry) {
        registerAllExtensions(
                (ExtensionRegistryLite) registry);
    }

    public interface GeoIpCityOrBuilder extends
            // @@protoc_insertion_point(interface_extends:mayton.geo.GeoIpCity)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>required int32 startIpNum = 1;</code>
         */
        boolean hasStartIpNum();

        /**
         * <code>required int32 startIpNum = 1;</code>
         */
        int getStartIpNum();

        /**
         * <code>required int32 endIpNum = 2;</code>
         */
        boolean hasEndIpNum();

        /**
         * <code>required int32 endIpNum = 2;</code>
         */
        int getEndIpNum();

        /**
         * <code>optional string country = 3;</code>
         */
        boolean hasCountry();

        /**
         * <code>optional string country = 3;</code>
         */
        String getCountry();

        /**
         * <code>optional string country = 3;</code>
         */
        ByteString
        getCountryBytes();

        /**
         * <code>optional string region = 4;</code>
         */
        boolean hasRegion();

        /**
         * <code>optional string region = 4;</code>
         */
        String getRegion();

        /**
         * <code>optional string region = 4;</code>
         */
        ByteString
        getRegionBytes();

        /**
         * <code>optional string city = 5;</code>
         */
        boolean hasCity();

        /**
         * <code>optional string city = 5;</code>
         */
        String getCity();

        /**
         * <code>optional string city = 5;</code>
         */
        ByteString
        getCityBytes();

        /**
         * <code>optional string postalCode = 6;</code>
         */
        boolean hasPostalCode();

        /**
         * <code>optional string postalCode = 6;</code>
         */
        String getPostalCode();

        /**
         * <code>optional string postalCode = 6;</code>
         */
        ByteString
        getPostalCodeBytes();

        /**
         * <code>required double latitude = 7;</code>
         */
        boolean hasLatitude();

        /**
         * <code>required double latitude = 7;</code>
         */
        double getLatitude();

        /**
         * <code>required double longitude = 8;</code>
         */
        boolean hasLongitude();

        /**
         * <code>required double longitude = 8;</code>
         */
        double getLongitude();

        /**
         * <code>optional string dmaCode = 9;</code>
         */
        boolean hasDmaCode();

        /**
         * <code>optional string dmaCode = 9;</code>
         */
        String getDmaCode();

        /**
         * <code>optional string dmaCode = 9;</code>
         */
        ByteString
        getDmaCodeBytes();

        /**
         * <code>optional string areaCode = 10;</code>
         */
        boolean hasAreaCode();

        /**
         * <code>optional string areaCode = 10;</code>
         */
        String getAreaCode();

        /**
         * <code>optional string areaCode = 10;</code>
         */
        ByteString
        getAreaCodeBytes();
    }

    /**
     * Protobuf type {@code mayton.geo.GeoIpCity}
     */
    public static final class GeoIpCity extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:mayton.geo.GeoIpCity)
            GeoIpCityOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use GeoIpCity.newBuilder() to construct.
        private GeoIpCity(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private GeoIpCity() {
            startIpNum_ = 0;
            endIpNum_ = 0;
            country_ = "";
            region_ = "";
            city_ = "";
            postalCode_ = "";
            latitude_ = 0D;
            longitude_ = 0D;
            dmaCode_ = "";
            areaCode_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return mayton.protobufgenerated.GeoIpEntity.internal_static_mayton_geo_GeoIpCity_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return mayton.protobufgenerated.GeoIpEntity.internal_static_mayton_geo_GeoIpCity_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            mayton.protobufgenerated.GeoIpEntity.GeoIpCity.class, mayton.protobufgenerated.GeoIpEntity.GeoIpCity.Builder.class);
        }

        private int bitField0_;
        public static final int STARTIPNUM_FIELD_NUMBER = 1;
        private int startIpNum_;

        /**
         * <code>required int32 startIpNum = 1;</code>
         */
        public boolean hasStartIpNum() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        /**
         * <code>required int32 startIpNum = 1;</code>
         */
        public int getStartIpNum() {
            return startIpNum_;
        }

        public static final int ENDIPNUM_FIELD_NUMBER = 2;
        private int endIpNum_;

        /**
         * <code>required int32 endIpNum = 2;</code>
         */
        public boolean hasEndIpNum() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        /**
         * <code>required int32 endIpNum = 2;</code>
         */
        public int getEndIpNum() {
            return endIpNum_;
        }

        public static final int COUNTRY_FIELD_NUMBER = 3;
        private volatile Object country_;

        /**
         * <code>optional string country = 3;</code>
         */
        public boolean hasCountry() {
            return ((bitField0_ & 0x00000004) == 0x00000004);
        }

        /**
         * <code>optional string country = 3;</code>
         */
        public String getCountry() {
            Object ref = country_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    country_ = s;
                }
                return s;
            }
        }

        /**
         * <code>optional string country = 3;</code>
         */
        public ByteString
        getCountryBytes() {
            Object ref = country_;
            if (ref instanceof java.lang.String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                country_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        public static final int REGION_FIELD_NUMBER = 4;
        private volatile Object region_;

        /**
         * <code>optional string region = 4;</code>
         */
        public boolean hasRegion() {
            return ((bitField0_ & 0x00000008) == 0x00000008);
        }

        /**
         * <code>optional string region = 4;</code>
         */
        public String getRegion() {
            Object ref = region_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    region_ = s;
                }
                return s;
            }
        }

        /**
         * <code>optional string region = 4;</code>
         */
        public ByteString
        getRegionBytes() {
            Object ref = region_;
            if (ref instanceof java.lang.String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                region_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        public static final int CITY_FIELD_NUMBER = 5;
        private volatile Object city_;

        /**
         * <code>optional string city = 5;</code>
         */
        public boolean hasCity() {
            return ((bitField0_ & 0x00000010) == 0x00000010);
        }

        /**
         * <code>optional string city = 5;</code>
         */
        public String getCity() {
            Object ref = city_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    city_ = s;
                }
                return s;
            }
        }

        /**
         * <code>optional string city = 5;</code>
         */
        public ByteString
        getCityBytes() {
            Object ref = city_;
            if (ref instanceof java.lang.String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                city_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        public static final int POSTALCODE_FIELD_NUMBER = 6;
        private volatile Object postalCode_;

        /**
         * <code>optional string postalCode = 6;</code>
         */
        public boolean hasPostalCode() {
            return ((bitField0_ & 0x00000020) == 0x00000020);
        }

        /**
         * <code>optional string postalCode = 6;</code>
         */
        public String getPostalCode() {
            Object ref = postalCode_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    postalCode_ = s;
                }
                return s;
            }
        }

        /**
         * <code>optional string postalCode = 6;</code>
         */
        public ByteString
        getPostalCodeBytes() {
            Object ref = postalCode_;
            if (ref instanceof java.lang.String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                postalCode_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        public static final int LATITUDE_FIELD_NUMBER = 7;
        private double latitude_;

        /**
         * <code>required double latitude = 7;</code>
         */
        public boolean hasLatitude() {
            return ((bitField0_ & 0x00000040) == 0x00000040);
        }

        /**
         * <code>required double latitude = 7;</code>
         */
        public double getLatitude() {
            return latitude_;
        }

        public static final int LONGITUDE_FIELD_NUMBER = 8;
        private double longitude_;

        /**
         * <code>required double longitude = 8;</code>
         */
        public boolean hasLongitude() {
            return ((bitField0_ & 0x00000080) == 0x00000080);
        }

        /**
         * <code>required double longitude = 8;</code>
         */
        public double getLongitude() {
            return longitude_;
        }

        public static final int DMACODE_FIELD_NUMBER = 9;
        private volatile Object dmaCode_;

        /**
         * <code>optional string dmaCode = 9;</code>
         */
        public boolean hasDmaCode() {
            return ((bitField0_ & 0x00000100) == 0x00000100);
        }

        /**
         * <code>optional string dmaCode = 9;</code>
         */
        public String getDmaCode() {
            Object ref = dmaCode_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    dmaCode_ = s;
                }
                return s;
            }
        }

        /**
         * <code>optional string dmaCode = 9;</code>
         */
        public ByteString
        getDmaCodeBytes() {
            Object ref = dmaCode_;
            if (ref instanceof java.lang.String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                dmaCode_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        public static final int AREACODE_FIELD_NUMBER = 10;
        private volatile Object areaCode_;

        /**
         * <code>optional string areaCode = 10;</code>
         */
        public boolean hasAreaCode() {
            return ((bitField0_ & 0x00000200) == 0x00000200);
        }

        /**
         * <code>optional string areaCode = 10;</code>
         */
        public String getAreaCode() {
            Object ref = areaCode_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                if (bs.isValidUtf8()) {
                    areaCode_ = s;
                }
                return s;
            }
        }

        /**
         * <code>optional string areaCode = 10;</code>
         */
        public ByteString
        getAreaCodeBytes() {
            Object ref = areaCode_;
            if (ref instanceof java.lang.String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                areaCode_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                java.nio.ByteBuffer data,
                ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                ByteString data,
                ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                byte[] data,
                ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                java.io.InputStream input,
                ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseDelimitedFrom(
                java.io.InputStream input,
                ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity parseFrom(
                com.google.protobuf.CodedInputStream input,
                ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(mayton.protobufgenerated.GeoIpEntity.GeoIpCity prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        /**
         * Protobuf type {@code mayton.geo.GeoIpCity}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:mayton.geo.GeoIpCity)
                mayton.protobufgenerated.GeoIpEntity.GeoIpCityOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return mayton.protobufgenerated.GeoIpEntity.internal_static_mayton_geo_GeoIpCity_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return mayton.protobufgenerated.GeoIpEntity.internal_static_mayton_geo_GeoIpCity_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                mayton.protobufgenerated.GeoIpEntity.GeoIpCity.class, mayton.protobufgenerated.GeoIpEntity.GeoIpCity.Builder.class);
            }

            // Construct using mayton.geo.GeoIpEntity.GeoIpCity.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                startIpNum_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                endIpNum_ = 0;
                bitField0_ = (bitField0_ & ~0x00000002);
                country_ = "";
                bitField0_ = (bitField0_ & ~0x00000004);
                region_ = "";
                bitField0_ = (bitField0_ & ~0x00000008);
                city_ = "";
                bitField0_ = (bitField0_ & ~0x00000010);
                postalCode_ = "";
                bitField0_ = (bitField0_ & ~0x00000020);
                latitude_ = 0D;
                bitField0_ = (bitField0_ & ~0x00000040);
                longitude_ = 0D;
                bitField0_ = (bitField0_ & ~0x00000080);
                dmaCode_ = "";
                bitField0_ = (bitField0_ & ~0x00000100);
                areaCode_ = "";
                bitField0_ = (bitField0_ & ~0x00000200);
                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return mayton.protobufgenerated.GeoIpEntity.internal_static_mayton_geo_GeoIpCity_descriptor;
            }

            @java.lang.Override
            public mayton.protobufgenerated.GeoIpEntity.GeoIpCity getDefaultInstanceForType() {
                return mayton.protobufgenerated.GeoIpEntity.GeoIpCity.getDefaultInstance();
            }

            @java.lang.Override
            public mayton.protobufgenerated.GeoIpEntity.GeoIpCity build() {
                mayton.protobufgenerated.GeoIpEntity.GeoIpCity result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public mayton.protobufgenerated.GeoIpEntity.GeoIpCity buildPartial() {
                mayton.protobufgenerated.GeoIpEntity.GeoIpCity result = new mayton.protobufgenerated.GeoIpEntity.GeoIpCity(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.startIpNum_ = startIpNum_;
                if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                    to_bitField0_ |= 0x00000002;
                }
                result.endIpNum_ = endIpNum_;
                if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
                    to_bitField0_ |= 0x00000004;
                }
                result.country_ = country_;
                if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
                    to_bitField0_ |= 0x00000008;
                }
                result.region_ = region_;
                if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
                    to_bitField0_ |= 0x00000010;
                }
                result.city_ = city_;
                if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
                    to_bitField0_ |= 0x00000020;
                }
                result.postalCode_ = postalCode_;
                if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
                    to_bitField0_ |= 0x00000040;
                }
                result.latitude_ = latitude_;
                if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
                    to_bitField0_ |= 0x00000080;
                }
                result.longitude_ = longitude_;
                if (((from_bitField0_ & 0x00000100) == 0x00000100)) {
                    to_bitField0_ |= 0x00000100;
                }
                result.dmaCode_ = dmaCode_;
                if (((from_bitField0_ & 0x00000200) == 0x00000200)) {
                    to_bitField0_ |= 0x00000200;
                }
                result.areaCode_ = areaCode_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            private int bitField0_;

            private int startIpNum_;

            /**
             * <code>required int32 startIpNum = 1;</code>
             */
            public boolean hasStartIpNum() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }

            /**
             * <code>required int32 startIpNum = 1;</code>
             */
            public int getStartIpNum() {
                return startIpNum_;
            }

            /**
             * <code>required int32 startIpNum = 1;</code>
             */
            public Builder setStartIpNum(int value) {
                bitField0_ |= 0x00000001;
                startIpNum_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required int32 startIpNum = 1;</code>
             */
            public Builder clearStartIpNum() {
                bitField0_ = (bitField0_ & ~0x00000001);
                startIpNum_ = 0;
                onChanged();
                return this;
            }

            private int endIpNum_;

            /**
             * <code>required int32 endIpNum = 2;</code>
             */
            public boolean hasEndIpNum() {
                return ((bitField0_ & 0x00000002) == 0x00000002);
            }

            /**
             * <code>required int32 endIpNum = 2;</code>
             */
            public int getEndIpNum() {
                return endIpNum_;
            }

            /**
             * <code>required int32 endIpNum = 2;</code>
             */
            public Builder setEndIpNum(int value) {
                bitField0_ |= 0x00000002;
                endIpNum_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required int32 endIpNum = 2;</code>
             */
            public Builder clearEndIpNum() {
                bitField0_ = (bitField0_ & ~0x00000002);
                endIpNum_ = 0;
                onChanged();
                return this;
            }

            private Object country_ = "";

            /**
             * <code>optional string country = 3;</code>
             */
            public boolean hasCountry() {
                return ((bitField0_ & 0x00000004) == 0x00000004);
            }

            /**
             * <code>optional string country = 3;</code>
             */
            public String getCountry() {
                Object ref = country_;
                if (!(ref instanceof java.lang.String)) {
                    ByteString bs =
                            (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        country_ = s;
                    }
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>optional string country = 3;</code>
             */
            public ByteString
            getCountryBytes() {
                Object ref = country_;
                if (ref instanceof String) {
                    ByteString b =
                            ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    country_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>optional string country = 3;</code>
             */
            public Builder setCountry(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000004;
                country_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>optional string country = 3;</code>
             */
            public Builder clearCountry() {
                bitField0_ = (bitField0_ & ~0x00000004);
                country_ = getDefaultInstance().getCountry();
                onChanged();
                return this;
            }

            /**
             * <code>optional string country = 3;</code>
             */
            public Builder setCountryBytes(
                    ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000004;
                country_ = value;
                onChanged();
                return this;
            }

            private Object region_ = "";

            /**
             * <code>optional string region = 4;</code>
             */
            public boolean hasRegion() {
                return ((bitField0_ & 0x00000008) == 0x00000008);
            }

            /**
             * <code>optional string region = 4;</code>
             */
            public String getRegion() {
                Object ref = region_;
                if (!(ref instanceof java.lang.String)) {
                    ByteString bs =
                            (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        region_ = s;
                    }
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>optional string region = 4;</code>
             */
            public ByteString
            getRegionBytes() {
                Object ref = region_;
                if (ref instanceof String) {
                    ByteString b =
                            ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    region_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>optional string region = 4;</code>
             */
            public Builder setRegion(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000008;
                region_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>optional string region = 4;</code>
             */
            public Builder clearRegion() {
                bitField0_ = (bitField0_ & ~0x00000008);
                region_ = getDefaultInstance().getRegion();
                onChanged();
                return this;
            }

            /**
             * <code>optional string region = 4;</code>
             */
            public Builder setRegionBytes(
                    ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000008;
                region_ = value;
                onChanged();
                return this;
            }

            private Object city_ = "";

            /**
             * <code>optional string city = 5;</code>
             */
            public boolean hasCity() {
                return ((bitField0_ & 0x00000010) == 0x00000010);
            }

            /**
             * <code>optional string city = 5;</code>
             */
            public String getCity() {
                Object ref = city_;
                if (!(ref instanceof java.lang.String)) {
                    ByteString bs =
                            (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        city_ = s;
                    }
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>optional string city = 5;</code>
             */
            public ByteString
            getCityBytes() {
                Object ref = city_;
                if (ref instanceof String) {
                    ByteString b =
                            ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    city_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>optional string city = 5;</code>
             */
            public Builder setCity(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000010;
                city_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>optional string city = 5;</code>
             */
            public Builder clearCity() {
                bitField0_ = (bitField0_ & ~0x00000010);
                city_ = getDefaultInstance().getCity();
                onChanged();
                return this;
            }

            /**
             * <code>optional string city = 5;</code>
             */
            public Builder setCityBytes(
                    ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000010;
                city_ = value;
                onChanged();
                return this;
            }

            private Object postalCode_ = "";

            /**
             * <code>optional string postalCode = 6;</code>
             */
            public boolean hasPostalCode() {
                return ((bitField0_ & 0x00000020) == 0x00000020);
            }

            /**
             * <code>optional string postalCode = 6;</code>
             */
            public String getPostalCode() {
                Object ref = postalCode_;
                if (!(ref instanceof java.lang.String)) {
                    ByteString bs =
                            (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        postalCode_ = s;
                    }
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>optional string postalCode = 6;</code>
             */
            public ByteString
            getPostalCodeBytes() {
                Object ref = postalCode_;
                if (ref instanceof String) {
                    ByteString b =
                            ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    postalCode_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>optional string postalCode = 6;</code>
             */
            public Builder setPostalCode(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000020;
                postalCode_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>optional string postalCode = 6;</code>
             */
            public Builder clearPostalCode() {
                bitField0_ = (bitField0_ & ~0x00000020);
                postalCode_ = getDefaultInstance().getPostalCode();
                onChanged();
                return this;
            }

            /**
             * <code>optional string postalCode = 6;</code>
             */
            public Builder setPostalCodeBytes(
                    ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000020;
                postalCode_ = value;
                onChanged();
                return this;
            }

            private double latitude_;

            /**
             * <code>required double latitude = 7;</code>
             */
            public boolean hasLatitude() {
                return ((bitField0_ & 0x00000040) == 0x00000040);
            }

            /**
             * <code>required double latitude = 7;</code>
             */
            public double getLatitude() {
                return latitude_;
            }

            /**
             * <code>required double latitude = 7;</code>
             */
            public Builder setLatitude(double value) {
                bitField0_ |= 0x00000040;
                latitude_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required double latitude = 7;</code>
             */
            public Builder clearLatitude() {
                bitField0_ = (bitField0_ & ~0x00000040);
                latitude_ = 0D;
                onChanged();
                return this;
            }

            private double longitude_;

            /**
             * <code>required double longitude = 8;</code>
             */
            public boolean hasLongitude() {
                return ((bitField0_ & 0x00000080) == 0x00000080);
            }

            /**
             * <code>required double longitude = 8;</code>
             */
            public double getLongitude() {
                return longitude_;
            }

            /**
             * <code>required double longitude = 8;</code>
             */
            public Builder setLongitude(double value) {
                bitField0_ |= 0x00000080;
                longitude_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>required double longitude = 8;</code>
             */
            public Builder clearLongitude() {
                bitField0_ = (bitField0_ & ~0x00000080);
                longitude_ = 0D;
                onChanged();
                return this;
            }

            private Object dmaCode_ = "";

            /**
             * <code>optional string dmaCode = 9;</code>
             */
            public boolean hasDmaCode() {
                return ((bitField0_ & 0x00000100) == 0x00000100);
            }

            /**
             * <code>optional string dmaCode = 9;</code>
             */
            public String getDmaCode() {
                Object ref = dmaCode_;
                if (!(ref instanceof java.lang.String)) {
                    ByteString bs =
                            (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        dmaCode_ = s;
                    }
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>optional string dmaCode = 9;</code>
             */
            public ByteString
            getDmaCodeBytes() {
                Object ref = dmaCode_;
                if (ref instanceof String) {
                    ByteString b =
                            ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    dmaCode_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>optional string dmaCode = 9;</code>
             */
            public Builder setDmaCode(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000100;
                dmaCode_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>optional string dmaCode = 9;</code>
             */
            public Builder clearDmaCode() {
                bitField0_ = (bitField0_ & ~0x00000100);
                dmaCode_ = getDefaultInstance().getDmaCode();
                onChanged();
                return this;
            }

            /**
             * <code>optional string dmaCode = 9;</code>
             */
            public Builder setDmaCodeBytes(
                    ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000100;
                dmaCode_ = value;
                onChanged();
                return this;
            }

            private Object areaCode_ = "";

            /**
             * <code>optional string areaCode = 10;</code>
             */
            public boolean hasAreaCode() {
                return ((bitField0_ & 0x00000200) == 0x00000200);
            }

            /**
             * <code>optional string areaCode = 10;</code>
             */
            public String getAreaCode() {
                Object ref = areaCode_;
                if (!(ref instanceof java.lang.String)) {
                    ByteString bs =
                            (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        areaCode_ = s;
                    }
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>optional string areaCode = 10;</code>
             */
            public ByteString
            getAreaCodeBytes() {
                Object ref = areaCode_;
                if (ref instanceof String) {
                    ByteString b =
                            ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    areaCode_ = b;
                    return b;
                } else {
                    return (ByteString) ref;
                }
            }

            /**
             * <code>optional string areaCode = 10;</code>
             */
            public Builder setAreaCode(
                    String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000200;
                areaCode_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>optional string areaCode = 10;</code>
             */
            public Builder clearAreaCode() {
                bitField0_ = (bitField0_ & ~0x00000200);
                areaCode_ = getDefaultInstance().getAreaCode();
                onChanged();
                return this;
            }

            /**
             * <code>optional string areaCode = 10;</code>
             */
            public Builder setAreaCodeBytes(
                    ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000200;
                areaCode_ = value;
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:mayton.geo.GeoIpCity)
        }

        // @@protoc_insertion_point(class_scope:mayton.geo.GeoIpCity)
        private static final mayton.protobufgenerated.GeoIpEntity.GeoIpCity DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE = new mayton.protobufgenerated.GeoIpEntity.GeoIpCity();
        }

        public static mayton.protobufgenerated.GeoIpEntity.GeoIpCity getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @java.lang.Deprecated
        public static final com.google.protobuf.Parser<GeoIpCity>
                PARSER = new com.google.protobuf.AbstractParser<GeoIpCity>() {
            @java.lang.Override
            public GeoIpCity parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                Builder builder = newBuilder();
                try {
                    builder.mergeFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(builder.buildPartial());
                } catch (java.io.IOException e) {
                    throw new com.google.protobuf.InvalidProtocolBufferException(
                            e.getMessage()).setUnfinishedMessage(
                            builder.buildPartial());
                }
                return builder.buildPartial();
            }
        };

        public static com.google.protobuf.Parser<GeoIpCity> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<GeoIpCity> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public mayton.protobufgenerated.GeoIpEntity.GeoIpCity getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_mayton_geo_GeoIpCity_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_mayton_geo_GeoIpCity_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\032proto2/geo-ip-entity.proto\022\nmayton.geo" +
                        "\"\274\001\n\tGeoIpCity\022\022\n\nstartIpNum\030\001 \002(\005\022\020\n\010en" +
                        "dIpNum\030\002 \002(\005\022\017\n\007country\030\003 \001(\t\022\016\n\006region\030" +
                        "\004 \001(\t\022\014\n\004city\030\005 \001(\t\022\022\n\npostalCode\030\006 \001(\t\022" +
                        "\020\n\010latitude\030\007 \002(\001\022\021\n\tlongitude\030\010 \002(\001\022\017\n\007" +
                        "dmaCode\030\t \001(\t\022\020\n\010areaCode\030\n \001(\tB\016\n\nmayto" +
                        "n.geoH\002"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
        internal_static_mayton_geo_GeoIpCity_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_mayton_geo_GeoIpCity_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_mayton_geo_GeoIpCity_descriptor,
                new java.lang.String[]{"StartIpNum", "EndIpNum", "Country", "Region", "City", "PostalCode", "Latitude", "Longitude", "DmaCode", "AreaCode",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}
