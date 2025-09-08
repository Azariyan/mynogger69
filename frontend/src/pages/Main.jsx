import { useState } from 'react'
import Input from '../components/Input/Input.jsx'
import '../App.css'

function Main() {
  const [username, setUsername] = useState(''); // исправлено setUsername
  const [password, setPassword] = useState('');
  const [response, setResponse] = useState('');

  const handleSubmit = async () => {
    const data = { username: username, password }; // используем username вместо login

    try {
      const res = await fetch('https://mynogger69-1.onrender.com/api/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
      });

      console.log('Отправленные данные:', data);
      console.log('Тип данных:', typeof data);
      console.log('Отправленные данные JSON:', JSON.stringify(data));

      const result = await res.json();
      console.log('Ответ от бэка:', result);
      setResponse(JSON.stringify(result)); // можно показать результат на фронте
    } catch (err) {
      console.error('Ошибка fetch:', err);
      setResponse('Ошибка соединения');
    }
  };

  return (
    <>
      <Input
        label="Login"
        value={username}
        onChange={e => setUsername(e.target.value)}
        placeholder="Enter your login"
        type="text"
        name="login"
        id="login"
      />
      <Input
        label="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
        placeholder="Enter your password"
        type="password"
        name="password"
        id="password"
      />
      <button onClick={handleSubmit}>Submit</button>
      {response && <p>Ответ: {response}</p>}
    </>
  );
}

export default Main;