import { Routes } from '@angular/router';
import { AddProductComponent } from "./pages/add-product/add-product.component";
import { HomePageComponent } from './pages/home-page/home-page.component';

export const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'add-product', component: AddProductComponent}
];
