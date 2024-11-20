package br.com.fiap.config;

final class DatabaseConfig {

    private DatabaseConfig() {
        throw new UnsupportedOperationException();
    }

    static String getUrl(){
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    }

    static String getUser(){
        return "RM558128";
    }

    static String getPassword(){
        return "210501";
    }

}
