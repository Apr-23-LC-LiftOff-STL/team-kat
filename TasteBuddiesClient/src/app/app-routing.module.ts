import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component'; 
import { AccountComponent } from './account/account.component';
import { UserEventsComponent } from './user-events/user-events.component';
import { PageNotFoundComponent } from './errors/page-not-found/page-not-found.component';
import { AuthGuardService as AuthGuard } from 'src/services/auth-guard.service';
import { EventFormComponent } from './event/event-form/event-form.component';

const routes: Routes = [
  { path: '', title: 'Taste Buddies', component: HomeComponent },
  { path: 'login', title: 'Login', component: LoginComponent },
  { path: 'signup', title: 'Register', component: RegistrationComponent },
  { path: 'account', title: 'Account', component: AccountComponent, canActivate: [AuthGuard] },
  { path: 'event', title: 'Events', component: UserEventsComponent, canActivate: [AuthGuard] },
  { path: 'event/create', title: 'New event', component: EventFormComponent, canActivate: [AuthGuard] },
  { path: '**', title: 'Page not found', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
