import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  private apiKey = 'your_google_maps_api_key';
  private apiUrl = 'https://pollen.googleapis.com/v1/forecast:lookup';

  constructor(private http: HttpClient) {}

  getPollenData(latitude: number, longitude: number): Observable<any> {
    return this.http.get(this.apiUrl, {
      params: {
        'location.latitude': latitude.toString(),
        'location.longitude': longitude.toString(),
        key: this.apiKey
      }
    });
  }

  getUserLocation(): Promise<GeolocationPosition> {
    return new Promise((resolve, reject) => {
      if (!navigator.geolocation) {
        reject('Geolocation is not supported');
      }
      navigator.geolocation.getCurrentPosition(resolve, reject);
    });
  }
} 