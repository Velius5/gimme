// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Ustawienia$$ViewBinder<T extends zespolowe.pl.aplikacja.Ustawienia> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493045, "field '_dodaj_imie_ust'");
    target._dodaj_imie_ust = finder.castView(view, 2131493045, "field '_dodaj_imie_ust'");
    view = finder.findRequiredView(source, 2131493046, "field '_dodaj_nazwisko_ust'");
    target._dodaj_nazwisko_ust = finder.castView(view, 2131493046, "field '_dodaj_nazwisko_ust'");
    view = finder.findRequiredView(source, 2131493051, "field '_btn_Ustawiena_zapisz'");
    target._btn_Ustawiena_zapisz = finder.castView(view, 2131493051, "field '_btn_Ustawiena_zapisz'");
    view = finder.findRequiredView(source, 2131493054, "field '_btn_Ustawiena_Wyloguj'");
    target._btn_Ustawiena_Wyloguj = finder.castView(view, 2131493054, "field '_btn_Ustawiena_Wyloguj'");
    view = finder.findRequiredView(source, 2131493053, "field '_link_licencje'");
    target._link_licencje = finder.castView(view, 2131493053, "field '_link_licencje'");
    view = finder.findRequiredView(source, 2131493052, "field '_link_o_nas'");
    target._link_o_nas = finder.castView(view, 2131493052, "field '_link_o_nas'");
    view = finder.findRequiredView(source, 2131493047, "field '_password_ust_Text'");
    target._password_ust_Text = finder.castView(view, 2131493047, "field '_password_ust_Text'");
    view = finder.findRequiredView(source, 2131493048, "field '_password_ust_repeat_Text'");
    target._password_ust_repeat_Text = finder.castView(view, 2131493048, "field '_password_ust_repeat_Text'");
    view = finder.findRequiredView(source, 2131493050, "field '_dodaj_foto'");
    target._dodaj_foto = finder.castView(view, 2131493050, "field '_dodaj_foto'");
    view = finder.findRequiredView(source, 2131493049, "field '_imageprev'");
    target._imageprev = finder.castView(view, 2131493049, "field '_imageprev'");
  }

  @Override public void unbind(T target) {
    target._dodaj_imie_ust = null;
    target._dodaj_nazwisko_ust = null;
    target._btn_Ustawiena_zapisz = null;
    target._btn_Ustawiena_Wyloguj = null;
    target._link_licencje = null;
    target._link_o_nas = null;
    target._password_ust_Text = null;
    target._password_ust_repeat_Text = null;
    target._dodaj_foto = null;
    target._imageprev = null;
  }
}
