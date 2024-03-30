import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss', './sidebar.responsive.component.scss']
})
export class SidebarComponent {

  activeLink: string = 'employees';

  ngOnInit() {}

  setActiveLink(linkName: string) {
    this.activeLink = linkName;
  }

}
