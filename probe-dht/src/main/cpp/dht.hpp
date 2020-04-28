class dht : public rttable {

                void            recv_ping(void *msg, sockaddr *from,
                                          int fromlen);
                void            recv_ping_reply(void *msg, sockaddr *from,
                                                int fromlen);
                void            recv_find_node(void *msg, sockaddr *from);
                void            recv_find_node_reply(void *msg, int len,
                                                     sockaddr *from);
                void            recv_find_value(void *msg, int len,
                                                sockaddr *from);
                void            recv_find_value_reply(void *msg, int len,
                                                      sockaddr *from);
                void            recv_store(void *msg, int len, sockaddr *from);


                void            find_node(const uint160_t &dst,
                                          callback_find_node func);
                void            find_node(std::string host, int port,
                                          callback_find_node func);
                void            find_node(sockaddr *saddr,
                                          callback_find_node func);
                void            find_value(const uint160_t &dst,
                                           const void *key, uint16_t keylen,
                                           callback_find_value func);
                void            store(const uint160_t &id,
                                      const void *key, uint16_t keylen,
                                      const void *value, uint16_t valuelen,
                                      uint16_t ttl, bool is_unique);
                void            store(id_ptr id, boost::shared_array<char> key,
                                      uint16_t keylen,
                                      boost::shared_array<char> value,
                                      uint16_t valuelen, uint16_t ttl,
                                      id_ptr from, bool is_unique);


                void            set_enabled_dtun(bool flag);
                void            set_enabled_rdp(bool flag);
                bool            is_use_rdp() { return m_is_use_rdp; }

                virtual void    send_ping(cageaddr &dst, uint32_t nonce);

                void            find_nv(const uint160_t &dst,
                                        callback_func func, bool is_find_value,
                                        const void *key, int keylen);
                void            send_find(query_ptr q);
                void            send_find_node(cageaddr &dst, query_ptr q);
                void            send_find_value(cageaddr &dst, query_ptr q);

                void            refresh();
                void            restore();
                void            sweep_rdp();
                void            maintain();

                void            recvd_value(query_ptr q);
                void            remove_query(query_ptr q);

                void            add_sdata(stored_data &sdata, bool is_origin);
                void            erase_sdata(stored_data &sdata);
                void            insert2recvd_sdata(stored_data &sdata,
                                                   id_ptr id);
                int             dec_origin_sdata(stored_data &sdata);

}