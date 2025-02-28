import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { WeatherService } from '../../services/weather.service';
import { PollenData } from '../../models/weather.model';

@Component({
  selector: 'app-weather',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatProgressSpinnerModule],
  template: `
    <div class="weather-container" *ngIf="!error; else errorTpl">
      <div class="pollen-data" *ngIf="pollenData; else loading">
        <div class="pollen-risk" [class]="getRiskClass(pollenData.dailyInfo[0].pollenIndex.grass)">
          <h3>Grass Pollen</h3>
          <p class="risk">{{pollenData.dailyInfo[0].pollenIndex.grass}}</p>
          <p class="count">Count: {{pollenData.dailyInfo[0].pollenCount.grass}}</p>
        </div>
        
        <div class="pollen-risk" [class]="getRiskClass(pollenData.dailyInfo[0].pollenIndex.tree)">
          <h3>Tree Pollen</h3>
          <p class="risk">{{pollenData.dailyInfo[0].pollenIndex.tree}}</p>
          <p class="count">Count: {{pollenData.dailyInfo[0].pollenCount.tree}}</p>
        </div>
        
        <div class="pollen-risk" [class]="getRiskClass(pollenData.dailyInfo[0].pollenIndex.weed)">
          <h3>Weed Pollen</h3>
          <p class="risk">{{pollenData.dailyInfo[0].pollenIndex.weed}}</p>
          <p class="count">Count: {{pollenData.dailyInfo[0].pollenCount.weed}}</p>
        </div>
      </div>
    </div>

    <ng-template #loading>
      <div class="loading">
        <mat-spinner diameter="40"></mat-spinner>
        <p>Loading pollen data...</p>
      </div>
    </ng-template>

    <ng-template #errorTpl>
      <div class="error">
        <p>{{error}}</p>
      </div>
    </ng-template>
  `,
  styleUrls: ['./weather.component.scss']
})
export class WeatherComponent implements OnInit {
  pollenData: PollenData | null = null;
  error: string | null = null;

  constructor(private weatherService: WeatherService) {}

  ngOnInit() {
    this.loadPollenData();
  }

  async loadPollenData() {
    try {
      const position = await this.weatherService.getUserLocation();
      this.weatherService.getPollenData(
        position.coords.latitude,
        position.coords.longitude
      ).subscribe({
        next: (data: PollenData) => {
          this.pollenData = data;
        },
        error: (err) => {
          this.error = 'Unable to load pollen data';
          console.error('Pollen data error:', err);
        }
      });
    } catch (err) {
      this.error = 'Unable to get location';
      console.error('Location error:', err);
    }
  }

  getRiskClass(risk: string): string {
    return `risk-${risk.toLowerCase()}`;
  }
} 