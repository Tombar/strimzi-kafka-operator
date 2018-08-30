#!/bin/bash

# path were the Secret with certificates is mounted
CERTS=/etc/tls-sidecar/certs
# We need to combine all the cluster-ca certs into one
COMBINED_CA=/tmp/cluster-ca.crt
cat ${CERTS}/cluster-ca*.crt > "$COMBINED_CA"

CURRENT=${BASE_HOSTNAME}-${KAFKA_BROKER_ID}

echo "pid = /usr/local/var/run/stunnel.pid"
echo "foreground = yes"
echo "debug = info"

cat <<-EOF
[zookeeper-2181]
client = yes
CAfile = ${COMBINED_CA}
cert = ${CERTS}/${CURRENT}.crt
key = ${CERTS}/${CURRENT}.key
accept = 127.0.0.1:2181
connect = ${KAFKA_ZOOKEEPER_CONNECT:-zookeeper-client:2181}
verify = 2

EOF