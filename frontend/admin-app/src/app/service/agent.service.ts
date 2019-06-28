import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })

  export class AgentService 
  {
      constructor(private http: HttpClient){}

      addAgent(agentDTO)
      {
        return this.http.post('/api/authservice/agents/', agentDTO, {responseType : "text", observe: 'response'});
      }

      checkIfUsernameIsValid(username)
      {
        return this.http.get('/api/authservice/agents/' + username, {observe: 'response'});
      }

      approveAgent(username){
        return this.http.post('/api/authservice/agents/approve', username);
      }

  }