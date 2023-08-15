import React from 'react';

type props = {
    prompt: string,
    setPrompt: React.Dispatch<React.SetStateAction<string>>
    handleSubmit: () => void
}

export const InputForm = ({ setPrompt, prompt,handleSubmit }: props) => {

    return (
        <div className='fixed flex  justify-center items-center bottom-0 left-0 w-full text-center p-4  '>
            <textarea
                className='my-2 py-3 px-4 mx-2 mr-2 focus:border-blue-400 rounded-xl w-[50%] 
                max-w-[1000px] max-h-[300px] min-h-[100px] bg-gray-100 drop-shadow-2xl '
                placeholder='Enter your prompt'
                onChange={(e) => setPrompt(e.target.value)}
                value={prompt}
            />
            <div className='bg-blue-600 text-white rounded-full px-3 py-3 my-2 cursor-pointer
                            hover:bg-blue-500'
                    onClick={handleSubmit}>
                Go
            </div>
        </div>
    );
};
