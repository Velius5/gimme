// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Menu_Activity$$ViewBinder<T extends zespolowe.pl.aplikacja.Menu_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493003, "field 'camera'");
    target.camera = finder.castView(view, 2131493003, "field 'camera'");
    view = finder.findRequiredView(source, 2131493004, "field 'gallery'");
    target.gallery = finder.castView(view, 2131493004, "field 'gallery'");
    view = finder.findRequiredView(source, 2131493005, "field 'znajomi'");
    target.znajomi = finder.castView(view, 2131493005, "field 'znajomi'");
    view = finder.findRequiredView(source, 2131493006, "field 'profil'");
    target.profil = finder.castView(view, 2131493006, "field 'profil'");
    view = finder.findRequiredView(source, 2131493008, "field 'ustawienia'");
    target.ustawienia = finder.castView(view, 2131493008, "field 'ustawienia'");
  }

  @Override public void unbind(T target) {
    target.camera = null;
    target.gallery = null;
    target.znajomi = null;
    target.profil = null;
    target.ustawienia = null;
  }
}
