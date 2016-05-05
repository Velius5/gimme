// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class User_Profile_Activity$$ViewBinder<T extends zespolowe.pl.aplikacja.User_Profile_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493039, "field '_profil_foto'");
    target._profil_foto = finder.castView(view, 2131493039, "field '_profil_foto'");
    view = finder.findRequiredView(source, 2131493040, "field '_profil_imie'");
    target._profil_imie = finder.castView(view, 2131493040, "field '_profil_imie'");
    view = finder.findRequiredView(source, 2131493041, "field '_profil_nazwisko'");
    target._profil_nazwisko = finder.castView(view, 2131493041, "field '_profil_nazwisko'");
    view = finder.findRequiredView(source, 2131493042, "field '_profil_email'");
    target._profil_email = finder.castView(view, 2131493042, "field '_profil_email'");
    view = finder.findRequiredView(source, 2131493043, "field '_dodaj_znajomego'");
    target._dodaj_znajomego = finder.castView(view, 2131493043, "field '_dodaj_znajomego'");
  }

  @Override public void unbind(T target) {
    target._profil_foto = null;
    target._profil_imie = null;
    target._profil_nazwisko = null;
    target._profil_email = null;
    target._dodaj_znajomego = null;
  }
}
