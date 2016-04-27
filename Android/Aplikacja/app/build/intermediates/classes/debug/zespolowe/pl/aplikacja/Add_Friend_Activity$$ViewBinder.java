// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Add_Friend_Activity$$ViewBinder<T extends zespolowe.pl.aplikacja.Add_Friend_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492982, "field '_search_friend'");
    target._search_friend = finder.castView(view, 2131492982, "field '_search_friend'");
    view = finder.findRequiredView(source, 2131492983, "field '_btn_znajdz_znajomych'");
    target._btn_znajdz_znajomych = finder.castView(view, 2131492983, "field '_btn_znajdz_znajomych'");
  }

  @Override public void unbind(T target) {
    target._search_friend = null;
    target._btn_znajdz_znajomych = null;
  }
}
