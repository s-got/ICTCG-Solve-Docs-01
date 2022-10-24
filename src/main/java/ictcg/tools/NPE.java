package ictcg.tools;

import java.util.Optional;
import java.util.function.Function;

public class NPE<T> {

	final T t;

	private NPE(T t) {
		this.t = t;
	}

	public <R> NPE<R> next(Function<? super T, ? extends R> mapper) {
		return t == null ? NPE.of(null) : NPE.of(mapper.apply(t));
	}

	public <R> R last(Function<? super T, ? extends R> mapper) {
		return t == null ? null : mapper.apply(t);
	}

	public <R> R last(Function<? super T, ? extends R> mapper, R defaultValue) {
		return t == null ? defaultValue : mapper.apply(t);
	}

	public <R> Optional<R> lastOptional(Function<? super T, ? extends R> mapper) {
		return t == null ? Optional.empty() : Optional.ofNullable(mapper.apply(t));
	}

	public T get() {
		return t;
	}

	public Optional<T> getOptional() {
		return Optional.ofNullable(t);
	}

	public static <X> NPE<X> of(X x) {
		return new NPE<X>(x);
	}

//	public static void main(String[] args) {
//		Invoice i = null;
////		i.getInbox().getThirdParty().getBookId();
//		String s = NPE.of(i).next(a -> a.getInbox()).next(a -> a.getThirdParty()).next(a -> a.getBookId()).get();
//		String s2 = NPE.of(i).next(a -> a.getInbox()).next(a -> a.getThirdParty()).last(a -> a.getBookId());
//		String s3 = NPE.of(i).next(Invoice::getInbox).next(Inbox::getThirdParty).last(ThirdParty::getBookId, "Unknown");
//		String s4 = NPE.of(i).next(Invoice::getInbox).next(Inbox::getThirdParty).lastOptional(ThirdParty::getBookId).orElse("Unknown");
//		System.err.println(s3);
//	}

}
