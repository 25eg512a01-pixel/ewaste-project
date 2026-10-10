const API_URL = (import.meta.env.VITE_API_URL || 'http://localhost:8080/api').replace(/\/$/, '');
export async function api(path, options = {}) {
  const token = localStorage.getItem('ewaste_token');
  const headers = { ...(options.body ? { 'Content-Type': 'application/json' } : {}), ...(token ? { Authorization: `Bearer ${token}` } : {}), ...(options.headers || {}) };
  const response = await fetch(`${API_URL}${path}`, { ...options, headers });
  if (response.status === 204) return null;
  const type = response.headers.get('content-type') || '';
  const data = type.includes('application/json') ? await response.json() : await response.text();
  if (!response.ok) throw new Error(data?.message || data?.error || (typeof data === 'string' ? data : `Request failed (${response.status})`));
  return data;
}
export const json = (method, body) => ({ method, body: JSON.stringify(body) });
