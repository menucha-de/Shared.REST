module havis.net.rest.shared {
    requires transitive javax.annotation.api;
    requires transitive javax.ws.rs.api;

    exports havis.net.rest.shared;
    exports havis.net.rest.shared.data;
    exports havis.net.rest.shared.provider;

}