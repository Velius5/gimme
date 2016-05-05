// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Log_Rej_Activity$$ViewBinder<T extends zespolowe.pl.aplikacja.Log_Rej_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492999, "field 'zaloguj_sie'");
    target.zaloguj_sie = finder.castView(view, 2131492999, "field 'zaloguj_sie'");
    view = finder.findRequiredView(source, 2131493001, "field 'zarejestuj_sie'");
    target.zarejestuj_sie = finder.castView(view, 2131493001, "field 'zarejestuj_sie'");
  }

  @Override public void unbind(T target) {
    target.zaloguj_sie = null;
    target.zarejestuj_sie = null;
  }
}
