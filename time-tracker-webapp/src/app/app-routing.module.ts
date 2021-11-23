import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateRecordComponent } from './create-record/create-record.component';
import { HomeComponent } from './home/home.component';
import { SearchFormComponent } from './search-form/search-form.component';

const routes: Routes = [{
  path: '',
  component: HomeComponent,
},
{
  path: 'search',
  component: SearchFormComponent,
},
{
  path: 'create',
  component: CreateRecordComponent,
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
