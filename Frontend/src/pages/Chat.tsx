import React from 'react'
import Header from '../components/Header'
import { InputForm } from '../components/InputForm'
import { useState } from 'react'
import UserQsn from '../components/UserQsn'
import Response from '../components/Response'


const Chat = () => {

    const [promptChange, setPromptChange] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(false);
    const [prompt, setPrompt] = useState<string>('');
    const [response, setResponse] = useState<string>();

    const handleSubmit = () => {
        setLoading(true);
        setPrompt(promptChange);
        setPromptChange('');
        console.log(prompt);
    }


    return (
        <div className=' min-h-screen '>
            <Header />
            <UserQsn prompt={prompt} />
            <Response />
            <InputForm setPrompt={setPromptChange} prompt={promptChange} handleSubmit={handleSubmit} />

        </div>
    )
}

export default Chat