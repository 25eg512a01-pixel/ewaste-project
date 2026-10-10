import { createContext, useContext, useMemo, useState } from 'react';
import { api, json } from '../api';
const AuthContext = createContext(null);
export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => { try { return JSON.parse(localStorage.getItem('ewaste_user') || 'null'); } catch { return null; } });
  const [busy, setBusy] = useState(false);
  async function authenticate(path, payload) {
    setBusy(true);
    try { const result = await api(path, json('POST', payload)); localStorage.setItem('ewaste_token', result.token); localStorage.setItem('ewaste_user', JSON.stringify(result.user)); setUser(result.user); return result.user; }
    finally { setBusy(false); }
  }
  const value = useMemo(() => ({ user, busy, login: p => authenticate('/auth/login', p), register: p => authenticate('/auth/register', p), logout: () => { localStorage.removeItem('ewaste_token'); localStorage.removeItem('ewaste_user'); setUser(null); } }), [user, busy]);
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
export function useAuth() { return useContext(AuthContext); }
