package com.reactive.springreactive.chap02.search_engine;

import java.net.URL;

public interface IterableSearchEngine {

    Iterable<URL> search(String query, int limit);
}
