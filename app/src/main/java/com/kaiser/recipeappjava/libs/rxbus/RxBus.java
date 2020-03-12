package com.kaiser.recipeappjava.libs.rxbus;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {
    private static final RxBus INSTANCE = new RxBus();

    private final Subject<Object, Object> mBusSubject = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance() {
        return INSTANCE;
    }

    public <T> Subscription register(final Class<T> eventClass, Action1<T> onNext) {
//        return mBusSubject
//                .filter(event -> event.getClass().equals(eventClass))
//                .map(obj -> (T) obj)
//                .subscribe(onNext);
        return mBusSubject
                .filter(new Func1<Object, Boolean>() {
                    @Override
                    public Boolean call(Object event) {
                        return event.getClass().equals(eventClass);
                    }
                })
                .map(new Func1<Object, T>() {
                    @Override
                    public T call(Object obj) {
                        return (T) obj;
                    }
                })
                .subscribe(onNext);
    }

    public void post(Object event) {
        mBusSubject.onNext(event);
    }

    public Observable<Object> toObserverable() {
        return mBusSubject;
    }

    public boolean hasObservers() {
        return mBusSubject.hasObservers();
    }
}
