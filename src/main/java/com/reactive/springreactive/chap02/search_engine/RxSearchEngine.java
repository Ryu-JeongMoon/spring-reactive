package com.reactive.springreactive.chap02.search_engine;

import io.reactivex.Observable;
import java.net.URL;

public interface RxSearchEngine {

    Observable<URL> search(String query, int limit);
}
